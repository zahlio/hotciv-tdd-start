package hotciv.common;

import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackStrategy;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.framework.UnitActionStrategy;
import hotciv.framework.CivFactory;
import hotciv.framework.WinStrategy;
import hotciv.framework.WorldLayoutStrategy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
	private UnitActionStrategy actionStrategy;
	private WorldLayoutStrategy layoutStrategy;
	private AttackStrategy attackAndDefenceStrategy;

	private HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();

	public GameImpl(CivFactory civFactory) {
		ageStrategy = civFactory.createAging();
		actionStrategy = civFactory.createUnitAction();
		layoutStrategy = civFactory.createLayout();
		winner = civFactory.createWinner();
		attackAndDefenceStrategy = civFactory.createAttack();
		units.put(new Position(2,0), new UnitImpl(GameConstants.ARCHER, Player.RED));
		units.put(new Position(3,2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
		units.put(new Position(4,3), new UnitImpl(GameConstants.SETTLER, Player.RED));
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

	//BAD SOLUTION
	public AttackStrategy getAttackStrategy(){
		return attackAndDefenceStrategy;
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
		if ( getUnitAt(from) == null ) { return false; }
		if ( getUnitAt(from).getOwner() != getPlayerInTurn() ) { return false; }
		if ( getTileAt(to).getTypeString().equals(GameConstants.OCEANS) ) { return false; }
		if ( getTileAt(to).getTypeString().equals(GameConstants.MOUNTAINS) ) { return false; }
		if (getUnitAt(from).getMoveCount()==0){ return false; }
		if (!canMoveDistance(from, to)) { return false; }

		if(getUnitAt(to) != null && getUnitAt(to).getOwner() != currentPlayer && attackAndDefenceStrategy.performAttack(this, from, to)==1){

		}else if(getUnitAt(to) != null && getUnitAt(to).getOwner() != currentPlayer && attackAndDefenceStrategy.performAttack(this, from, to)==2){
			units.remove(from);
			return true;
		}
		if(getCityAt(to) != null && getCityAt(to).getOwner() != currentPlayer){
			CityImpl c = (CityImpl) getCityAt(to);
			c.changeOwner(c.getOwner());
		}
		if(getTileAt(to).getTypeString().equals(GameConstants.PLAINS) || getTileAt(to).getTypeString().equals(GameConstants.HILLS)){}
		units.put(to, (UnitImpl) getUnitAt(from));
		units.remove(from);
		((UnitImpl) getUnitAt(to)).setHasMoved(true);
		return true;
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

	public void produceUnitAt(String unit, Position p){
		units.put(getNextSpawnPositionAtCity(p), new UnitImpl(unit, currentPlayer));
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

	public void moveUnitTo(Position from, Position to){
		units.put(to, (UnitImpl) getUnitAt(from));
		units.remove(from);
		((UnitImpl) getUnitAt(to)).setHasMoved(true);
	}

	public void endOfTurn() {
		if(currentPlayer == Player.RED){
			currentPlayer = Player.BLUE;
		}else if(currentPlayer == Player.BLUE){
			currentPlayer = Player.RED;
			setResourcesForEachCityAndProduceUnitIfCan();
			resetMoveCount();
			age = ageStrategy.getNewAge(age);
		}
	}

	private void resetMoveCount(){
		Iterator<Entry<Position, UnitImpl>> it = units.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Position, UnitImpl> pairs = it.next();
			Position p = pairs.getKey();
			((UnitImpl) getUnitAt(p)).setHasMoved(false);
		}
	}

	private void setResourcesForEachCityAndProduceUnitIfCan(){
		Iterator<Entry<Position, CityImpl>> it = cities.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Position, CityImpl> pairs = it.next();
			Position p = pairs.getKey();
			((CityImpl) getCityAt(p)).setResourcesPerRound();
			CityImpl c = (CityImpl) getCityAt(p);
			if(c.canDeductResources(c.getProduction()) && getNextSpawnPositionAtCity(p) != null){
				produceUnitAt(c.getProduction(), getNextSpawnPositionAtCity(p));
				c.deductResources(c.getProduction());
				produceUnitAt(c.getProduction(), p);
				c.setProduction("");
			}
		}
	}

	public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
	public void changeProductionInCityAt( Position p, String unitType ) {
		CityImpl c = (CityImpl) getCityAt(p);
		c.setProduction(unitType);
	}

	public void performUnitActionAt(Position p) {
		actionStrategy.performUnitAction(p, this);
	}

}
