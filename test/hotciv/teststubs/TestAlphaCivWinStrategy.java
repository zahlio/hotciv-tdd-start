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
import hotciv.variants.wincondition.AlphaCivWinCondition;

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

	public GameStub(int age) { this.age = age; }

	public Tile getTileAt(Position p) { return null; }
	public Unit getUnitAt(Position p) {	return null; }
	public City getCityAt(Position p) {	return null; }
	public Player getPlayerInTurn() { return null; }
	public Player getWinner() {	return null; }
	public int getAge() { return age; }
	public int setAge(int setAge) { return age = setAge; }
	public boolean moveUnit(Position from, Position to) { return false; }

	public void endOfTurn() { age += 100; }
	
	public void changeWorkForceFocusInCityAt(Position p, String balance) {}
	public void changeProductionInCityAt(Position p, String unitType) {}
	public void performUnitActionAt(Position p) {}
	public HashMap<Position, UnitImpl> getUnits() {	return null; }
	public HashMap<Position, CityImpl> getCities() { return null; }

	@Override
	public void setTranscription(boolean toggle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeTranscription() {
		// TODO Auto-generated method stub
		
	}
}