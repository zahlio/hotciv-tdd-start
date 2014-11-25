package hotciv.teststubs;

import static org.junit.Assert.assertEquals;
import hotciv.common.CityImpl;
import hotciv.framework.common.City;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Tile;
import hotciv.framework.common.Worlds;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.AlphaCivWorldLayout;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestAlphaCivWorldLayout {

	private WorldLayoutStrategy worldLayoutStrategy;
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();

	@Before
	public void SetUp(){
		worldLayoutStrategy = new AlphaCivWorldLayout(Worlds.WORLD_1);
		worldLayoutStrategy.putCities(cities);
	}

	@Test
	public void shouldHaveOceans(){
		Tile t = worldLayoutStrategy.getTileAt(new Position(0,1));
		assertEquals("Should have oceans at (0,1)", GameConstants.OCEANS, t.getTypeString());
	}

	@Test
	public void shouldHaveHills(){
		Tile t = worldLayoutStrategy.getTileAt(new Position(1,0));
		assertEquals("Should have Hills at (1,0)", GameConstants.HILLS, t.getTypeString());
	}

	@Test
	public void shouldHaveMountains(){
		Tile t = worldLayoutStrategy.getTileAt(new Position(2,2));
		assertEquals("Should have mountains at (2,2)", GameConstants.MOUNTAINS, t.getTypeString());
	}

	@Test
	public void thereShouldBeARedCityAt1_1(){
		Position p = new Position(1,1);
		City c = cities.get(p);
		assertEquals("There should be a Red city at (1,1)", Player.RED, c.getOwner());
	}

	@Test
	public void thereShouldBeABlueCityAt4_1(){
		Position p = new Position(4,1);
		City c = cities.get(p);
		assertEquals("There should be a Blue city at (4,1)", Player.BLUE, c.getOwner());
	}
}
