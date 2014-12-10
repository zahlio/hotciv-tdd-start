package hotciv.teststubs;

import static org.junit.Assert.*;
import hotciv.common.CityImpl;
import hotciv.common.UnitImpl;
import hotciv.common.UnitInfo;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.framework.strategy.UnitActionStrategy;
import hotciv.throwable.NotAUnitException;
import hotciv.variants.unitaction.GammaCivUnitAction;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;


public class TestGammaCivStub {

	private UnitActionStrategy unitAction;
	private GammaCivTestStub stubGame;
	
	@Before
	public void SetUp(){
		stubGame = new GammaCivTestStub();
		unitAction = new GammaCivUnitAction();
	}
	
	@Test
	public void ArcherShouldBeFortified(){
		unitAction.performUnitAction(new Position(1,1), stubGame);
		assertEquals("Archer should be fortified", 6,stubGame.getUnitAt(new Position(1,1)).getDefensiveStrength());
	}
	
	@Test
	public void SettlerShouldHaveBuildCityAt4_3(){
		unitAction.performUnitAction(new Position(4,3), stubGame);
		assertEquals("at city should be built at 4,3", Player.RED, stubGame.getCityAt(new Position(4,3)).getOwner());
	}
}

class GammaCivTestStub implements Game{
	
	private HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();

	public GammaCivTestStub() {
		units.put(new Position(1,1), new UnitImpl(new UnitInfo(10,3,2),GameConstants.ARCHER,Player.RED));
		units.put(new Position(4,3), new UnitImpl(new UnitInfo(30,3,0),GameConstants.SETTLER,Player.RED));
	}
	
	@Override
	public Tile getTileAt(Position p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unit getUnitAt(Position p) {
		return units.get(p);
	}

	@Override
	public City getCityAt(Position p) {
		return cities.get(p);
	}

	@Override
	public HashMap<Position, UnitImpl> getUnits() {
		return units;
	}

	@Override
	public HashMap<Position, CityImpl> getCities() {
		return cities;
	}

	@Override
	public Player getPlayerInTurn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean moveUnit(Position from, Position to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void endOfTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeWorkForceFocusInCityAt(Position p, String balance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeProductionInCityAt(Position p, String unitType)
			throws NotAUnitException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void performUnitActionAt(Position p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addObserver(GameObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTileFocus(Position position) {
		// TODO Auto-generated method stub
		
	}
	
}