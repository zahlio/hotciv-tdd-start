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
import hotciv.variants.AlphaCivWinCondition;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestAlphaCivWinStrategy {

	private WinStrategy winStrategy;
	private GameStub gameStubTest1;
	private GameStub gameStubTest2;
	private GameStub gameStubTest3;
	/** Fixture for alphaciv testing. */

	@Before
	public void setUp() {
		winStrategy = new AlphaCivWinCondition();
		gameStubTest1 = new GameStub(-3000);
		gameStubTest2 = new GameStub(-3500);
		gameStubTest3 = new GameStub(-2000);
	}

	@Test
	public void winnerOfTheGameShouldBeRed(){
		assertEquals("Red Should have won by now", Player.RED, winStrategy.getWinner(gameStubTest1));
	}

	@Test
	public void thereShouldBeNoWinner(){
		assertNull("There should be no winner and should return null",winStrategy.getWinner(gameStubTest2));
	}

	@Test
	public void redShouldAlsoBeTheWinnerInYear2000(){
		assertEquals("Red should for sure have won by now", Player.RED, winStrategy.getWinner(gameStubTest3));
	}


}

class GameStub implements Game {
	private int age;

	public GameStub(int age) {
		// TODO Auto-generated constructor stub
		this.age = age;
	}

	@Override
	public Tile getTileAt(Position p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unit getUnitAt(Position p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City getCityAt(Position p) {
		// TODO Auto-generated method stub
		return null;
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
		return age;
	}

	public int setAge(int setAge) {
		return age = setAge;
	}

	@Override
	public boolean moveUnit(Position from, Position to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void endOfTurn() {
		// TODO Auto-generated method stub
		age += 100;
	}

	@Override
	public void changeWorkForceFocusInCityAt(Position p, String balance) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeProductionInCityAt(Position p, String unitType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void performUnitActionAt(Position p) {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<Position, UnitImpl> getUnits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Position, CityImpl> getCities() {
		// TODO Auto-generated method stub
		return null;
	}

}