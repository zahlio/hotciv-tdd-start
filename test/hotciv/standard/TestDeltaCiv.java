package hotciv.standard;

import static org.junit.Assert.assertEquals;
import hotciv.common.GameImpl;
import hotciv.factories.AlphaCivFactory;
import hotciv.factories.DeltaCivFactory;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.variants.AlphaCivAging;
import hotciv.variants.AlphaCivUnitAction;
import hotciv.variants.DeltaCivWorldLayout;

import org.junit.Before;
import org.junit.Test;

public class TestDeltaCiv {

	private Game game;
	/** Fixture for alphaciv testing. */
	@Before
	public void setUp() {
		game = new GameImpl(new DeltaCivFactory());
	}
	
	@Test
	public void ThereShouldBeARedCityAt8_12(){
		City c = game.getCityAt(new Position(8,12));
		assertEquals("There should be a Red City here", Player.RED, c.getOwner());
	}
	
	@Test
	public void ThereShouldBeABlueCityAt4_5(){
		City c = game.getCityAt(new Position(4,5));
		assertEquals("There should be a Red City here", Player.BLUE, c.getOwner());
	}
	
	@Test
	public void ThereShoulBeAForestAt4_4(){
		Tile t = game.getTileAt(new Position(4,4));
		assertEquals("There should be a forest here", GameConstants.FOREST, t.getTypeString());
	}
	
	@Test
	public void ThereShoulBeMountainsAt11_5(){
		Tile t = game.getTileAt(new Position(11,5));
		assertEquals("There should be mountains here", GameConstants.MOUNTAINS, t.getTypeString());
	}
	
	@Test
	public void ThereShoulBeOceansAt15_15(){
		Tile t = game.getTileAt(new Position(15,15));
		assertEquals("There should be oceans here", GameConstants.OCEANS, t.getTypeString());
	}
	
	@Test
	public void ThereShoulBePlainsAt0_3(){
		Tile t = game.getTileAt(new Position(0,3));
		assertEquals("There should be plains here", GameConstants.PLAINS, t.getTypeString());
	}
}
