package hotciv.common;

import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.common.City;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Tile;
import hotciv.framework.common.Unit;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitActionStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.throwable.NotAUnitException;

import java.util.HashMap;

/** Skeleton implementation of HotCiv.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University

   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

public class GameImpl implements Game {

	private Player currentPlayer = Player.RED;
	private int age = -4000;


	private AgingStrategy ageStrategy;
	private WinStrategy winner;
	private WorldLayoutStrategy layoutStrategy;
	private AttackStrategy attackAndDefenceStrategy;
	private UnitActionStrategy unitActionStrategy;

	private HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
	private HashMap<String, UnitInfo> unitsInGame;

	public GameImpl(CivFactory civFactory) {
		ageStrategy = civFactory.createAging();
		unitsInGame = civFactory.createUnit();
		layoutStrategy = civFactory.createLayout();
		winner = civFactory.createWinner();
		attackAndDefenceStrategy = civFactory.createAttack();
		unitActionStrategy = civFactory.createUnitAction();

		if(unitsInGame.containsKey(GameConstants.ARCHER) && unitsInGame.containsKey(GameConstants.LEGION) && unitsInGame.containsKey(GameConstants.SETTLER)){
			units.put(new Position(2,0), new UnitImpl(unitsInGame.get(GameConstants.ARCHER),GameConstants.ARCHER, Player.RED));
			units.put(new Position(3,2), new UnitImpl(unitsInGame.get(GameConstants.LEGION),GameConstants.LEGION, Player.BLUE));
			units.put(new Position(4,3), new UnitImpl(unitsInGame.get(GameConstants.SETTLER), GameConstants.SETTLER, Player.RED));
		}
		layoutStrategy.putCities(cities);


	}

	public Tile getTileAt(Position p) {
		return layoutStrategy.getTileAt(p);
	}

	public Unit getUnitAt(Position p) {
		return units.get(p);
	}

	public City getCityAt(Position p) {
		return cities.get(p);
	}

	public HashMap<Position, UnitImpl> getUnits(){
		return units;
	}

	public HashMap<Position, CityImpl> getCities(){
		return cities;
	}

	public Player getPlayerInTurn() { 
		return currentPlayer;
	}

	public Player getWinner() {
		return winner.getWinner(this);
	}

	public int getAge() { 
		return age;
	}

	public boolean moveUnit(Position from, Position to) {
		//CANT INITIALIZE THIS WAY
		boolean outcomeIsinitialized = false;
		//BAD WAY TO USE OUTCOME
		boolean outcome = false;

		if ( getUnitAt(from) == null ) { return false; }
		if ( getUnitAt(from).getOwner() != getPlayerInTurn() ) { return false; }
		if ( getTileAt(to).getTypeString().equals(GameConstants.OCEANS) ) { return false; }
		if ( getTileAt(to).getTypeString().equals(GameConstants.MOUNTAINS) ) { return false; }
		if (getUnitAt(from).getMoveCount()==0){ return false; }
		if (!canMoveDistance(from, to)) { return false; }

		if(getUnitAt(to) != null && getUnitAt(to).getOwner() != currentPlayer){
			outcomeIsinitialized = true;
			outcome = attackAndDefenceStrategy.performAttack(this, from, to);
		}
		if(outcome && outcomeIsinitialized && getCityAt(to) != null && getCityAt(to).getOwner() != currentPlayer){
			moveToTile(from, to, "cityAndUnit");
			return true;
		}
		if(outcome && outcomeIsinitialized){
			winner.setAttackCount(this);
			moveToTile(from, to, "unit");
			return true;
		}
		if(!outcome && outcomeIsinitialized){
			units.remove(from);
			return true;
		}
		if(getCityAt(to) != null && getCityAt(to).getOwner() != currentPlayer){
			moveToTile(from, to, "city");
			return true;
		}
		if(getTileAt(to).getTypeString().equals(GameConstants.PLAINS) && getUnitAt(to)==null || getTileAt(to).getTypeString().equals(GameConstants.HILLS) && getUnitAt(to)==null){
			moveToTile(from, to, "tile");
			return true; 
		}
		return false;
	}

	//MOVE TO OBJECT? THIS METHOD LOOKS UGLY
	public void moveToTile(Position current, Position other, String moveToType){
		if(moveToType.equals("unit")){
			units.remove(other);
		}
		if(moveToType.equals("city")){
			CityImpl c = (CityImpl) getCityAt(other);
			c.changeOwner();
		}
		if(moveToType.equals("cityAndUnit")){
			units.remove(other);
			CityImpl c = (CityImpl) getCityAt(other);
			c.changeOwner();
		}
		if(moveToType.equals("tile")){
		}

		units.put(other, (UnitImpl) getUnitAt(current));
		units.remove(current);
		((UnitImpl)getUnitAt(other)).setHasMoved(true);
	}

	public boolean canMoveDistance(Position from, Position to){
		int moveHorizontal = from.getRow() - to.getRow();
		int moveVertical = from.getColumn() - to.getColumn();
		if(getUnitAt(from).getMoveCount() >= Math.abs(moveHorizontal) && getUnitAt(from).getMoveCount() >= Math.abs(moveVertical)){
			return true;
		}else{
			return false;
		}
	}

	public void produceUnitAt(Position p, UnitInfo info, String unit, Player owner) {
		units.put(getNextSpawnPositionAtCity(p), new UnitImpl(unitsInGame.get(unit), unit, owner));
	}

	public Position getNextSpawnPositionAtCity(Position p){
		Position unitProduced = null;
		unitProduced = unitCanBePlacedAt(p);
		return unitProduced;
	}

	private Position unitCanBePlacedAt(Position p){
		Position placeAtPosition = null;
		int i = 0;

		while(i<9){
			if(getUnitAt(PlaceUnitAt(p, i))==null){
				placeAtPosition = PlaceUnitAt(p, i);
				i=9;
			}else{
				i++;
			}
		}
		return placeAtPosition;
	}

	private Position PlaceUnitAt(Position p, int number){
		int[] placeX = {0,-1,-1,0,1,1,1,0,-1}; 
		int[] placeY = {0,0,1,1,1,0,-1,-1,-1};

		return new Position(p.getRow()+placeX[number],p.getRow()+placeY[number]);
	}

	public void endOfTurn() {
		if(currentPlayer == Player.RED){
			currentPlayer = Player.BLUE;
		}else if(currentPlayer == Player.BLUE){
			currentPlayer = Player.RED;
			setResourcesForEachCityAndProduceUnitIfCan();
			resetUnitMoveCount();
			age = ageStrategy.getNewAge(age);
		}
	}

	private void resetUnitMoveCount(){
		for(Position p : units.keySet()){
			((UnitImpl)getUnitAt(p)).setHasMoved(false);
		}
	}

	private void setResourcesForEachCityAndProduceUnitIfCan(){
		for(Position p : cities.keySet()){
			CityImpl c = (CityImpl) getCityAt(p);
			c.setResourcesPerRound();
			if(c.canDeductResources(c.getProduction()) && getNextSpawnPositionAtCity(p) != null){
				produceUnitAt(getNextSpawnPositionAtCity(p),unitsInGame.get(c.getProduction()),c.getProduction(), c.getOwner());
				c.deductResources(c.getProduction());
				produceUnitAt(p,unitsInGame.get(c.getProduction()),c.getProduction(), c.getOwner());
				//TODO: eventually say how much
				c.setProduction("");
			}
		}
	}

	public void changeWorkForceFocusInCityAt( Position p, String balance ) {}


	public void changeProductionInCityAt( Position p, String unitType ) throws NotAUnitException {
		if(unitsInGame.containsKey(unitType)){
			CityImpl c = (CityImpl) getCityAt(p);
			c.setProduction(unitType);
		}else{
			throw new NotAUnitException(unitType);
		}
	}

	public void performUnitActionAt(Position p) {
		unitActionStrategy.performUnitAction(p, this);
	}
}
