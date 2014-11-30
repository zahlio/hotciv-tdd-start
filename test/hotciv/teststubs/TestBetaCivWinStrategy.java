package hotciv.teststubs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hotciv.common.CityImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.common.City;
import hotciv.framework.common.Game;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Tile;
import hotciv.framework.common.Unit;
import hotciv.framework.strategy.WinStrategy;
import hotciv.variants.wincondition.BetaCivWinCondition;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestBetaCivWinStrategy {
	private WinStrategy winStrategy;
	private GameStubBetaCiv gameStubTest1;
	private GameStubBetaCiv gameStubTest2;
	private GameStubBetaCiv gameStubTest3;
	private HashMap<Position, CityImpl> cities1 = new HashMap<Position, CityImpl>();
	private HashMap<Position, CityImpl> cities2 = new HashMap<Position, CityImpl>();
	private HashMap<Position, CityImpl> cities3 = new HashMap<Position, CityImpl>();


	@Before
	public void setUp(){
		winStrategy = new BetaCivWinCondition();
		
		cities1.put(new Position(1,1), new CityImpl(Player.RED));
		
		cities2.put(new Position(2,2), new CityImpl(Player.BLUE));
		
		cities3.put(new Position(1,1), new CityImpl(Player.RED));
		cities3.put(new Position(2,2), new CityImpl(Player.BLUE));
		
		gameStubTest1 = new GameStubBetaCiv(cities1);
		gameStubTest2 = new GameStubBetaCiv(cities2);
		gameStubTest3 = new GameStubBetaCiv(cities3);
	}

	@Test
	public void redShouldWin() {
		assertEquals("Red player should win", Player.RED, winStrategy.getWinner(gameStubTest1));
	}

	@Test
	public void bluePlayerShouldWin(){
		assertEquals("Blue player should win", Player.BLUE, winStrategy.getWinner(gameStubTest2));
	}

	@Test
	public void thereShouldBeNoWinner(){
		assertNull("Red and Blue have cities,no winner", winStrategy.getWinner(gameStubTest3));
	}
}

class GameStubBetaCiv implements Game{
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();

	public GameStubBetaCiv(HashMap<Position, CityImpl> cities){
		this.cities = cities;
	}

	@Override
	public Tile getTileAt(Position p) {
		return null;
	}

	@Override
	public Unit getUnitAt(Position p) {
		return null;
	}

	@Override
	public City getCityAt(Position p) {
		return null;
	}

	@Override
	public Player getPlayerInTurn() {
		return null;
	}

	@Override
	public Player getWinner() {
		return null;
	}

	@Override
	public int getAge() {
		return 0;
	}

	@Override
	public boolean moveUnit(Position from, Position to) {
		return false;
	}

	@Override
	public void endOfTurn() {
	}

	public HashMap<Position, CityImpl> getCities(){
		return cities;
	}

	@Override
	public void changeWorkForceFocusInCityAt(Position p, String balance) {

	}

	@Override
	public void changeProductionInCityAt(Position p, String unitType) {
	}

	@Override
	public void performUnitActionAt(Position p) {
	}

	@Override
	public HashMap<Position, UnitImpl> getUnits() {
		return null;
	}

}