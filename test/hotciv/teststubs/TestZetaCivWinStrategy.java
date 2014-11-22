package hotciv.teststubs;

import static org.junit.Assert.*;

import java.util.HashMap;

import hotciv.common.CityImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.AttackStrategy;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.framework.WinStrategy;
import hotciv.variants.ZetaCivAttacks;
import hotciv.variants.ZetaCivWinCondition;

import org.junit.Before;
import org.junit.Test;

public class TestZetaCivWinStrategy {

	private Game game;
	private Game game2;
	private WinStrategy zetaWin;
	private AttackStrategy zetaAttack;
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();
	
	@Before
	public void SetUp(){
		zetaAttack = new ZetaCivAttacks();
		zetaWin = new ZetaCivWinCondition();
		game = new GameStubZetaCiv(-3000, cities);
		game2 = new GameStubZetaCiv(-3000, cities);
	}
	
	@Test
	public void RedShouldHaveWonBetaCiv(){
		game.getCities().put(new Position(1,1), new CityImpl(Player.RED));
		assertEquals("Red should have been winner", Player.RED, zetaWin.getWinner(game));
	}
	
	@Test
	public void RedShouldHaveWonEpsilonCiv(){
		
	}
}

class GameStubZetaCiv implements Game{
	
	private int age;
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();

	public GameStubZetaCiv(int age, HashMap<Position, CityImpl> cities){
		this.age = age;
		this.cities = cities;
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
		return age;
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

	public HashMap<Position, CityImpl> getCities(){
		return cities;
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
}