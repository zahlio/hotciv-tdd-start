package hotciv.civs;

import hotciv.common.GameImpl;
import hotciv.factories.DeltaCivFactory;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;

import org.junit.Before;
import org.junit.Test;

public class TestDeltaCiv {
	private Game game;
	
	@Before
	public void setUp(){
		game = new GameImpl(new DeltaCivFactory());
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
}
