package hotciv.common;

import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.command.Command;
import hotciv.framework.common.City;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Tile;
import hotciv.framework.common.Unit;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitStrategy;
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


	private Command command;
	private AgingStrategy ageStrategy;
	private WinStrategy winner;
	private WorldLayoutStrategy layoutStrategy;
	private AttackStrategy attackAndDefenceStrategy;
	private UnitStrategy unitStrategy;

	private HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();

	public GameImpl(CivFactory civFactory) {
		ageStrategy = civFactory.createAging();
		unitStrategy = civFactory.createUnit();
		layoutStrategy = civFactory.createLayout();
		winner = civFactory.createWinner();
		attackAndDefenceStrategy = civFactory.createAttack();
		command = civFactory.createCommand();

		units.put(new Position(2,0), (UnitImpl) unitStrategy.produceUnit(GameConstants.ARCHER, Player.RED));
		units.put(new Position(3,2), (UnitImpl) unitStrategy.produceUnit(GameConstants.LEGION, Player.BLUE));
		units.put(new Position(4,3), (UnitImpl) unitStrategy.produceUnit(GameConstants.SETTLER, Player.RED));

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
			command.writeTranscript(getPlayerInTurn() + " moves " + getUnitAt(from).getTypeString() + " from " + from.toString() + " to " + to.toString());
			command.writeTranscript(" and kills " + getUnitAt(to).getOwner() + " " + getUnitAt(to).getTypeString());
			command.writeTranscript(" and conquers city at " + to.toString() + " owned by " + getCityAt(to).getOwner() + "\n");
			moveToTile(from, to, "cityAndUnit");
			return true;
		}
		if(outcome && outcomeIsinitialized){
			command.writeTranscript(getPlayerInTurn() + " moves " + getUnitAt(from).toString() + " from " + from.toString() + " to " + to.toString());
			command.writeTranscript("and kills " + getUnitAt(to).getOwner() + " " + getUnitAt(to).getTypeString() + "\n");
			winner.setAttackCount(this);
			moveToTile(from, to, "unit");
			return true;
		}
		if(!outcome && outcomeIsinitialized){
			command.writeTranscript(getUnitAt(from).getOwner() + " " + getUnitAt(from).getTypeString() + " has lost the battle.\n");
			units.remove(from);
			return true;
		}
		if(getCityAt(to) != null && getCityAt(to).getOwner() != currentPlayer){
			command.writeTranscript(currentPlayer + " " + getUnitAt(from).getTypeString() + " has conqured the city at " + to.toString() + " owned by " + getCityAt(to).getOwner() + "\n");
			moveToTile(from, to, "city");
			return true;
		}
		if(getTileAt(to).getTypeString().equals(GameConstants.PLAINS) && getUnitAt(to)==null || getTileAt(to).getTypeString().equals(GameConstants.HILLS) && getUnitAt(to)==null){
			command.writeTranscript(getUnitAt(from).getOwner() + " " + getUnitAt(from).getTypeString() + " has moved from" + from.toString() + " to a new position at " + to.toString() + "\n");
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
		getUnitAt(other).setHasMoved(true);
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

	public void produceUnitAt(String unit, Position p) {
		units.put(getNextSpawnPositionAtCity(p), (UnitImpl) unitStrategy.produceUnit(unit, currentPlayer));
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
			command.writeTranscript(currentPlayer + " ends turn.\n");
			currentPlayer = Player.BLUE;
		}else if(currentPlayer == Player.BLUE){
			command.writeTranscript(currentPlayer + " ends turn.\n");
			currentPlayer = Player.RED;
			setResourcesForEachCityAndProduceUnitIfCan();
			resetUnitMoveCount();
			age = ageStrategy.getNewAge(age);
			command.writeTranscript("The year is " + age + "\n");
		}
	}

	private void resetUnitMoveCount(){
		for(Position p : units.keySet()){
			getUnitAt(p).setHasMoved(false);
		}
		command.writeTranscript("All unit MoveCount has been reset\n");
	}

	private void setResourcesForEachCityAndProduceUnitIfCan(){
		command.writeTranscript("Resources for all cities have increased\n");
		for(Position p : cities.keySet()){
			CityImpl c = (CityImpl) getCityAt(p);
			c.setResourcesPerRound();
			if(c.canDeductResources(c.getProduction()) && getNextSpawnPositionAtCity(p) != null){
				produceUnitAt(c.getProduction(), getNextSpawnPositionAtCity(p));
				c.deductResources(c.getProduction());
				produceUnitAt(c.getProduction(), p);
				//TODO: eventually say how much
				command.writeTranscript(c.getOwner() + " city at " + p.toString() + " has had his resources deducted ");
				command.writeTranscript(c.getProduction() + " has been produced at " + getNextSpawnPositionAtCity(p).toString() + "\n");
				c.setProduction("");
			}
		}
	}

	public void changeWorkForceFocusInCityAt( Position p, String balance ) {}


	public void changeProductionInCityAt( Position p, String unitType ) throws NotAUnitException {
		if(unitStrategy.hasUnit(unitType)){
			CityImpl c = (CityImpl) getCityAt(p);
			c.setProduction(unitType);
			command.writeTranscript(getCityAt(p).getOwner() + " city at " + p.toString() + " changed it's production to: " + unitType + "\n");
		}else{
			command.writeTranscript(getCityAt(p).getOwner() + " city at" + p.toString() + " tried to produce" + unitType + " which does not exiest in this game\n");
			throw new NotAUnitException(unitType);
		}
	}

	public void performUnitActionAt(Position p) {
		unitStrategy.performUnitAction(this, p);
		if(getUnitAt(p)!=null){
			command.writeTranscript(getUnitAt(p).getOwner() + " " + getUnitAt(p).getTypeString() + " has performed it's action.\n");
		}
	}

	public void setTranscription(boolean toggle) {
		command.setTranscription(toggle);
	}

	@Override
	public void closeTranscription() {
		command.closeTranscript();
	}

}
