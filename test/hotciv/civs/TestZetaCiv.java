package hotciv.civs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.common.UnitInfo;
import hotciv.factories.ZetaCivFactory;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.variants.die.OneSidedDie;

import org.junit.Before;
import org.junit.Test;

public class TestZetaCiv {
	
	private Game game;
	
	@Before
	public void setUp() {
		game = new GameImpl(new ZetaCivFactory(new OneSidedDie()));
	}
	
	@Test
	public void RedArcherShouldHaveConquredBlueCityAndWonTheGameYear4000BC(){
		game.moveUnit(new Position(2,0), new Position(3,1));
		Utility.playRounds(game, 1);
		game.moveUnit(new Position(3,1), new Position(4,1));
		assertEquals("(4,1) should be owned by Red", Player.RED, game.getCityAt(new Position(4,1)).getOwner());
		assertEquals("Red Player should have won", Player.RED, game.getWinner());
	}
	
	@Test
	public void thereShouldbeNoWinner(){
		assertNull("No one should have won", game.getWinner());
		Utility.playRounds(game, 30);
		assertNull("No one should have won", game.getWinner());
	}
	
	@Test
	public void RedShouldBeWinnerIfHeWins3TimesYear1000BC(){

		Utility.playRounds(game, 30);
		
		game.getUnits().put(new Position(9,0), new UnitImpl(new UnitInfo(15,2,4),GameConstants.LEGION, Player.RED));
		game.getUnits().put(new Position(9,1), new UnitImpl(new UnitInfo(15,2,4),GameConstants.LEGION, Player.BLUE));
		game.getUnits().put(new Position(9,3), new UnitImpl(new UnitInfo(15,2,4),GameConstants.LEGION, Player.BLUE));
		game.getUnits().put(new Position(9,5), new UnitImpl(new UnitInfo(15,2,4),GameConstants.LEGION, Player.BLUE));

		game.moveUnit(new Position(9,0), new Position(9,1));
		Utility.playRounds(game,1);
		game.moveUnit(new Position(9,1), new Position(9,2));
		Utility.playRounds(game,1);
		game.moveUnit(new Position(9,2), new Position(9,3));
		Utility.playRounds(game,1);
		game.moveUnit(new Position(9,3), new Position(9,4));
		Utility.playRounds(game,1);
		game.moveUnit(new Position(9,4), new Position(9,5));

		assertEquals("Red should be winner if he succeed all attacks", Player.RED, game.getWinner());
	}

	@Test
	public void BlueshouldBeWinnerIfHeWins3TimesYear1000BC(){

		Utility.playRounds(game, 30);
		
		game.getUnits().put(new Position(10,0), new UnitImpl(new UnitInfo(15,2,4),GameConstants.LEGION, Player.BLUE));
		game.getUnits().put(new Position(10,1), new UnitImpl(new UnitInfo(15,2,4),GameConstants.LEGION, Player.RED));
		game.getUnits().put(new Position(10,3), new UnitImpl(new UnitInfo(15,2,4),GameConstants.LEGION, Player.RED));
		game.getUnits().put(new Position(10,5), new UnitImpl(new UnitInfo(15,2,4),GameConstants.LEGION, Player.RED));
		
		game.endOfTurn();

		game.moveUnit(new Position(10,0), new Position(10,1));
		Utility.playRounds(game,1);
		game.moveUnit(new Position(10,1), new Position(10,2));
		Utility.playRounds(game,1);
		game.moveUnit(new Position(10,2), new Position(10,3));
		Utility.playRounds(game,1);
		game.moveUnit(new Position(10,3), new Position(10,4));
		Utility.playRounds(game,1);
		game.moveUnit(new Position(10,4), new Position(10,5));

		assertEquals("blue should be winner if he succeed all attacks", Player.BLUE, game.getWinner());
	}
}
