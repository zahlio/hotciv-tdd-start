package hotciv.teststubs;

import hotciv.civs.Utility;
import hotciv.common.CityImpl;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Worlds;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.worldlayout.DeltaCivWorldLayout;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestDeltaCivWorldLayout {

	private WorldLayoutStrategy worldLayoutStrategy;
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();

	@Before
	public void SetUp(){
		worldLayoutStrategy = new DeltaCivWorldLayout(Worlds.WORLD_DELTA);
		worldLayoutStrategy.putCities(cities);
	}
	
	@Test
	public void shouldHaveOceansAt15_15(){
		Utility.shouldHaveTileatXY(worldLayoutStrategy, GameConstants.OCEANS, new Position(15,15));
	}

	@Test
	public void shouldHaveHillsAt1_3(){
		Utility.shouldHaveTileatXY(worldLayoutStrategy, GameConstants.HILLS, new Position(1,3));
	}

	@Test
	public void shouldHaveForestAt4_4(){
		Utility.shouldHaveTileatXY(worldLayoutStrategy, GameConstants.FOREST, new Position(4,4));
	}
	
	@Test
	public void shouldHaveMountainsAt11_5(){
		Utility.shouldHaveTileatXY(worldLayoutStrategy, GameConstants.MOUNTAINS, new Position(11,5));
	}

	@Test
	public void shouldHavePlainsAt8_12(){
		Utility.shouldHaveTileatXY(worldLayoutStrategy, GameConstants.PLAINS, new Position(8,12));
	}
	
	@Test
	public void ThereShouldBeARedCityAt(){
		Utility.thereShouldBeCityAtXY(cities, Player.RED, new Position(8,12));
	}

	@Test
	public void ThereShouldABlueCityAt(){
		Utility.thereShouldBeCityAtXY(cities, Player.BLUE, new Position(4,5));
	}
}
