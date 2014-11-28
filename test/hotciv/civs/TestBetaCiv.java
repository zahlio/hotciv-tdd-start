package hotciv.civs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hotciv.common.GameImpl;
import hotciv.factories.BetaCivFactory;
import hotciv.framework.common.Game;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;

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
	public void RedShouldbeWinner(){
		Utility.changeOwnerOfCity(game,new Position(4,1));
		assertEquals("Red should have won", Player.RED, game.getWinner());
	}
	
	@Test
	public void BlueShouldbeWinner(){
		Utility.changeOwnerOfCity(game,new Position(1,1));
		assertEquals("Blue should have won", Player.BLUE, game.getWinner());
	}
	
	//TEST IF YOU MOVE ON BLUE CITY
	
	@Test
	public void ThereShouldbeNoWinner(){
		assertNull("No one should have won", game.getWinner());
	}
}
