package hotciv.teststubs;

import static org.junit.Assert.*;
import hotciv.civs.Utility;
import hotciv.common.CityImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.framework.strategy.WinStrategy;
import hotciv.variants.wincondition.EpsilonWinCondition;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestEpsilonCivWinning {

	private WinStrategy epsilonWin;
	private Game game;

	@Before 
	public void SetUp(){
		game = new EpsilonWinStub();
		epsilonWin = new EpsilonWinCondition();
	}
	
	@Test 
	public void RedShouldBeWinnerIfHeWins3Times(){
		Utility.attack(game, epsilonWin, 3);
		assertEquals("Red should have won", Player.RED ,epsilonWin.getWinner(game));
	}
	
	@Test 
	public void BlueShouldBeWinnerIfHeWins3Times(){
		game.endOfTurn();
		Utility.attack(game, epsilonWin, 3);
		assertEquals("Blue should have won", Player.BLUE ,epsilonWin.getWinner(game));
	}
	
	@Test
	public void NoOneShouldHaveWon(){
		Utility.attack(game, epsilonWin, 2);
		assertNull("No one should have won", epsilonWin.getWinner(game));
	}
	
	@Test
	public void AttacksExpected(){
		Utility.attack(game, epsilonWin, 3);
		game.endOfTurn();
		Utility.attack(game, epsilonWin, 2);
		
		assertEquals("Red attacks should be 3", 3, epsilonWin.getRedAttacks());
		assertEquals("Blue attacks should be 2", 2, epsilonWin.getBlueAttacks());
	}
	
}

class EpsilonWinStub implements Game {

	private Player currentPlayer = Player.RED;

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
	public HashMap<Position, UnitImpl> getUnits() {
		return null;
	}

	@Override
	public HashMap<Position, CityImpl> getCities() {
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
		return 0;
	}

	@Override
	public boolean moveUnit(Position from, Position to) {
		return false;
	}

	@Override
	public void endOfTurn() {
		if(currentPlayer==Player.RED){
			currentPlayer=Player.BLUE;
		}else if(currentPlayer==Player.BLUE){
			currentPlayer=Player.RED;
		}
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
	public void addObserver(GameObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTileFocus(Position position) {
		// TODO Auto-generated method stub
		
	}
}