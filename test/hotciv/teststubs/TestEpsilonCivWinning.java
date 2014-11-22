package hotciv.teststubs;

import static org.junit.Assert.*;
import hotciv.common.CityImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.framework.WinStrategy;
import hotciv.variants.EpsilonWinCondition;

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
		epsilonWin.setAttackCount(game);
		epsilonWin.setAttackCount(game);
		epsilonWin.setAttackCount(game);
		assertEquals("Red should have won", Player.RED ,epsilonWin.getWinner(game));
	}
	
	@Test 
	public void BlueShouldBeWinnerIfHeWins3Times(){
		game.endOfTurn();
		epsilonWin.setAttackCount(game);
		epsilonWin.setAttackCount(game);
		epsilonWin.setAttackCount(game);
		assertEquals("Red should have won", Player.BLUE ,epsilonWin.getWinner(game));
	}
	
	@Test
	public void NoOneShouldHaveWon(){
		epsilonWin.setAttackCount(game);
		epsilonWin.setAttackCount(game);
		assertNull("No one should have won", epsilonWin.getWinner(game));
	}
}

class EpsilonWinStub implements Game {

	private Player currentPlayer = Player.RED;

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
	public HashMap<Position, UnitImpl> getUnits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Position, CityImpl> getCities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerInTurn() {
		return currentPlayer;
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
		if(currentPlayer==Player.RED){
			currentPlayer=Player.BLUE;
		}else if(currentPlayer==Player.BLUE){
			currentPlayer=Player.RED;
		}
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

}