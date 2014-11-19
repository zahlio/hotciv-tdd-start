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
import hotciv.framework.WinAndFightingFactory;
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
	private int redAttack, blueAttack = 0;
	private AgingStrategy ageStrategy;
	private WinStrategy winner;
	private UnitActionStrategy actionStrategy;
	private WorldLayoutStrategy layoutStrategy;
	private AttackStrategy attackAndDefenceStrategy;

	private HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();

	//If we can change the constructor then do it
	public GameImpl(AgingStrategy ageStrategy, UnitActionStrategy actionStrategy , WorldLayoutStrategy layoutStrategy, WinAndFightingFactory winAndFightingFactory) {
		this.ageStrategy = ageStrategy;
		this.actionStrategy = actionStrategy;
		this.layoutStrategy = layoutStrategy;
		winner = winAndFightingFactory.createWinner();
		attackAndDefenceStrategy = winAndFightingFactory.createAttack();
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
	
	public int getRedAttacks(){
		return redAttack;
	}
	
	public int getBlueAttacks(){
		return blueAttack;
	}
	
	public void setAttackCount(){
		if(currentPlayer==Player.RED){
		redAttack++;
		}else{
			blueAttack++;
		}
	}

	//IF UNIT TRIES TO MOVES MORE THAN ALLOWED RETURN FALSE
	public boolean moveUnit(Position from, Position to) {
		if ( getUnitAt(from) == null ) { return false; }
		if ( getUnitAt(from).getOwner() != getPlayerInTurn() ) { return false; }
		if ( getTileAt(to).getTypeString().equals(GameConstants.OCEANS) ) { return false; }
		if ( getTileAt(to).getTypeString().equals(GameConstants.MOUNTAINS) ) { return false; }
		if (!isInsideWorld(from) || !isInsideWorld(to)) { return false; }
		if (getUnitAt(from).getMoveCount()==0){ return false; }
		if (!canMoveDistance(from, to)) { return false; }

		if(getUnitAt(to) != null){
			if(getUnitAt(to).getOwner() != currentPlayer){
				attackAndDefenceStrategy.performAttack(this, from, to);
				((UnitImpl) getUnitAt(to)).setHasMoved(true);
			}
			return true;
		}else if(getTileAt(to).getTypeString().equals(GameConstants.PLAINS) || getTileAt(to).getTypeString().equals(GameConstants.HILLS)){
			units.put(to, (UnitImpl) getUnitAt(from));
			units.remove(from);
			((UnitImpl) getUnitAt(to)).setHasMoved(true);
			return true;
		}
		else { return true; }
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

	public boolean isInsideWorld(Position p){
		if(p.getRow() >= 0 && p.getRow() < GameConstants.WORLDSIZE &&
				p.getColumn() >= 0 && p.getColumn() < GameConstants.WORLDSIZE){
			return true;
		}else{
			return false;
		}
	}

	public void produceUnit(String unit, Position p){
		ProduceUnit pu = new ProduceUnit(this);
		if(pu.canProduceUnitAt(p) != null && pu.accumulatedEnoughProduction(unit, p)){
			units.put(pu.canProduceUnitAt(p), new UnitImpl(unit, currentPlayer));
			CityImpl c = (CityImpl) getCityAt(p);
			c.deductResources(unit);
		}
	}

	public void endOfTurn() {
		if(currentPlayer == Player.RED){
			currentPlayer = Player.BLUE;
		}else if(currentPlayer == Player.BLUE){
			currentPlayer = Player.RED;
			age = ageStrategy.getNewAge(age);
			setResources();
			resetMoveCount();
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

	private void setResources(){
		Iterator<Entry<Position, CityImpl>> it = cities.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Position, CityImpl> pairs = it.next();
			Position p = pairs.getKey();
			((CityImpl) getCityAt(p)).setResourcesPerRound();
		}
	}

	public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
	public void changeProductionInCityAt( Position p, String unitType ) {
		//THIS METHOD CHANGES THE PRODUCTION IN THE CITY - FOR ALPHACIV
	}

	public void performUnitActionAt(Position p) {
		actionStrategy.performUnitAction(p, this);
	}

}
