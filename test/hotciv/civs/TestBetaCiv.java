package hotciv.civs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hotciv.common.GameImpl;
import hotciv.factories.BetaCivFactory;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;

import org.junit.Before;
import org.junit.Test;

public class TestBetaCiv {

	private Game game;
	/** Fixture for alphaciv testing. */
	@Before
	public void setUp() {
		game = new GameImpl(new BetaCivFactory());
	}

	@Test
	public void AgeShouldBeYear2000BC(){
		Utility.playRounds(game,20);
		assertEquals("Game year should be -2000", -2000, game.getAge());
	}

	@Test
	public void AgeShouldBeYear1BC(){
		Utility.playRounds(game,40);
		assertEquals("Game year should be -1", -1, game.getAge());
	}

	@Test
	public void AgeShouldBeYear0BC(){
		Utility.playRounds(game,41);
		assertEquals("Game year should be 0", 0, game.getAge());
	}

	@Test
	public void AgeShouldBeYear1AD(){
		Utility.playRounds(game,42);
		assertEquals("Game year should be 1", 1, game.getAge());
	}

	@Test
	public void AgeShouldBeYear50AD(){
		Utility.playRounds(game,43);
		assertEquals("Game year should be 50", 50, game.getAge());
	}

	@Test
	public void AgeShouldBeYear1400AD(){
		Utility.playRounds(game,70);
		assertEquals("Game year should be 1400", 1400, game.getAge());
	}

	@Test
	public void AgeShouldBeYear1825AD(){
		Utility.playRounds(game,80);
		assertEquals("Game year should be 1825", 1825, game.getAge());
	}

	@Test
	public void AgeShouldBeYear1930AD(){
		Utility.playRounds(game,89);
		assertEquals("Game year should be 1930", 1930, game.getAge());
	}

	@Test
	public void AgeShouldBeYear2014AD(){
		Utility.playRounds(game,141);
		assertEquals("Game year should be 2014", 2014, game.getAge());
	}

	@Test
	public void RedArcherShouldHaveConquredBlueCityAndWonTheGame(){
		game.moveUnit(new Position(2,0), new Position(3,1));
		Utility.playRounds(game, 1);
		game.moveUnit(new Position(3,1), new Position(4,1));
		assertEquals("(4,1) should be owned by Red", Player.RED, game.getCityAt(new Position(4,1)).getOwner());
		assertEquals("Red Player should have won", Player.RED, game.getWinner());
	}
	
	@Test
	public void ThereShouldbeNoWinner(){
		assertNull("No one should have won", game.getWinner());
	}
}
