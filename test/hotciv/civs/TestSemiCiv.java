package hotciv.civs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.common.UnitInfo;
import hotciv.factories.SemiCivFactory;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Unit;
import hotciv.variants.die.OneSidedDie;

import org.junit.Before;
import org.junit.Test;

public class TestSemiCiv {
	
	private Game game;
	
	@Before
	public void setup(){
		game = new GameImpl(new SemiCivFactory(new OneSidedDie()));
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
	public void settlerShouldHaveCreatedACityAt4_3(){
		game.performUnitActionAt(new Position(4,3));
		settlerShouldHaveCreatedACityAt(4, 3, Player.RED);
	}
	
	public void settlerShouldHaveCreatedACityAt(int x, int y, Player p){
		assertEquals(
				String.format("There should be a city at (%d, %d) owned by player %s", x, y, p), 
				p, 
				game.getCityAt(new Position(x, y)).getOwner()
		);
	}
	
	@Test
	public void shouldHaveOceansAt15_15(){
		Utility.civShouldHaveTileatXY(game, GameConstants.OCEANS, new Position(15,15));
	}

	@Test
	public void shouldHaveHillsAt1_3(){
		Utility.civShouldHaveTileatXY(game, GameConstants.HILLS, new Position(1,3));
	}

	@Test
	public void shouldHaveForestAt4_4(){
		Utility.civShouldHaveTileatXY(game, GameConstants.FOREST, new Position(4,4));
	}
	
	@Test
	public void shouldHaveMountainsAt11_5(){
		Utility.civShouldHaveTileatXY(game, GameConstants.MOUNTAINS, new Position(11,5));
	}

	@Test
	public void shouldHavePlainsAt8_12(){
		Utility.civShouldHaveTileatXY(game, GameConstants.PLAINS, new Position(8,12));
	}
	
	@Test
	public void ThereShouldBeARedCityAt(){
		Utility.civThereShouldBeCityAtXY(game, Player.RED, new Position(8,12));
	}

	@Test
	public void ThereShouldABlueCityAt(){
		Utility.civThereShouldBeCityAtXY(game, Player.BLUE, new Position(4,5));
	}
	
	@Test
	public void RedShouldBeWinnerIfHeWins3Times(){
		
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
		Unit u = game.getUnitAt(new Position(9,5));

		if(u.getOwner()==Player.RED){
			assertEquals("Red should be winner if he succeed all attacks", Player.RED, game.getWinner());
		}else{
			assertNull("There should be no winner", game.getWinner());
		}
	}

}
