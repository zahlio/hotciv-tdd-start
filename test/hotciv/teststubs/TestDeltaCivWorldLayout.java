package hotciv.teststubs;

import static org.junit.Assert.assertEquals;
import hotciv.common.CityImpl;
import hotciv.framework.common.City;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Tile;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.DeltaCivWorldLayout;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestDeltaCivWorldLayout {

	private WorldLayoutStrategy worldLayoutStrategy;
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();

	@Before
	public void SetUp(){
		worldLayoutStrategy = new DeltaCivWorldLayout();
		worldLayoutStrategy.putCities(cities);
	}

	@Test
	public void shouldHaveOceansAt15_15(){
		Tile t = worldLayoutStrategy.getTileAt(new Position(15,15));
		assertEquals("Should have oceans at (15,15)", GameConstants.OCEANS, t.getTypeString());
	}

	@Test
	public void shouldHaveHillsAt1_3(){
		Tile t = worldLayoutStrategy.getTileAt(new Position(1,3));
		assertEquals("Should have Hills at (1,3)", GameConstants.HILLS, t.getTypeString());
	}
	
	@Test
	public void shouldHaveForestAt4_4(){
		Tile t = worldLayoutStrategy.getTileAt(new Position(4,4));
		assertEquals("Should have Forest at (4,4)", GameConstants.FOREST, t.getTypeString());
	}

	@Test
	public void shouldHaveMountainsAt11_5(){
		Tile t = worldLayoutStrategy.getTileAt(new Position(11,5));
		assertEquals("Should have mountains at (2,2)", GameConstants.MOUNTAINS, t.getTypeString());
	}
	
	@Test
	public void shouldHavePlainsAt0_3(){
		Tile t = worldLayoutStrategy.getTileAt(new Position(0,3));
		assertEquals("Should have mountains at (0,3)", GameConstants.PLAINS, t.getTypeString());
	}

	@Test
	public void thereShouldBeARedCityAt8_12(){
		Position p = new Position(8,12);
		City c = cities.get(p);
		assertEquals("There should be a Red city at (8,12)", Player.RED, c.getOwner());
	}

	@Test
	public void thereShouldBeABlueCityAt4_5(){
		Position p = new Position(4,5);
		City c = cities.get(p);
		assertEquals("There should be a Blue city at (4,5)", Player.BLUE, c.getOwner());
	}
}
