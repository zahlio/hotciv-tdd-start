package hotciv.teststubs;

import static org.junit.Assert.*;
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
import hotciv.variants.wincondition.EpsilonWinCondition;
import hotciv.variants.wincondition.ZetaCivWinCondition;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestZetaCivWinStrategy {

	private Game game;
	private Game game2;
	private WinStrategy zetaWin;
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
	
	@Before
	public void SetUp(){
		zetaWin = new ZetaCivWinCondition(new BetaCivWinCondition(), new EpsilonWinCondition());
		game = new GameStubZetaCiv(-3000, cities);
		game2 = new GameStubZetaCiv(-1000, cities);
	}
	
	@Test
	public void RedShouldHaveWonBetaCiv(){
		game.getCities().put(new Position(1,1), new CityImpl(Player.RED));
		assertEquals("Red should have won BetaCiv", Player.RED, zetaWin.getWinner(game));
	}
	
	@Test
	public void BlueShouldHaveWonBetaCiv(){
		game.getCities().put(new Position(4,1), new CityImpl(Player.BLUE));
		assertEquals("Blue should have won BetaCiv", Player.BLUE, zetaWin.getWinner(game));
	}
	
	@Test
	public void NoOneHasWonBetaCiv(){
		game.getCities().put(new Position(1,1), new CityImpl(Player.RED));
		game.getCities().put(new Position(4,1), new CityImpl(Player.BLUE));
		assertNull("No one should have won BetaCiv", zetaWin.getWinner(game));
	}
	
	@Test
	public void RedShouldHaveWonEpsilonCiv(){
		zetaWin.setAttackCount(game2);
		zetaWin.setAttackCount(game2);
		zetaWin.setAttackCount(game2);
		assertEquals("Red should have won EpsilonCiv", Player.RED, zetaWin.getWinner(game2));
	}
	
	@Test
	public void BlueShouldHaveWonEpsilonCiv(){
		game2.endOfTurn();
		zetaWin.setAttackCount(game2);
		zetaWin.setAttackCount(game2);
		zetaWin.setAttackCount(game2);
		assertEquals("Blue should have won EpsilonCiv", Player.BLUE, zetaWin.getWinner(game2));
	}
	
	@Test
	public void NoShouldHaveWonEpsilonCiv(){
		zetaWin.setAttackCount(game2);
		zetaWin.setAttackCount(game2);
		assertNull("No one should have won EpsilonCiv", zetaWin.getWinner(game2));
	}
}

class GameStubZetaCiv implements Game{
	
	private Player currentPlayer = Player.RED;
	private int age;
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();

	public GameStubZetaCiv(int age, HashMap<Position, CityImpl> cities){
		this.age = age;
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
		return currentPlayer;
	}

	@Override
	public Player getWinner() {
		return null;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public boolean moveUnit(Position from, Position to) {
		return false;
	}

	@Override
	public void endOfTurn() {
		if(currentPlayer==Player.RED){
			currentPlayer = Player.BLUE;
		}else if(currentPlayer==Player.BLUE){
			currentPlayer = Player.RED;
		}

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