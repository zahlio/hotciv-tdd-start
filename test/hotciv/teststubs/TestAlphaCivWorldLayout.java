package hotciv.teststubs;

import hotciv.civs.Utility;
import hotciv.common.CityImpl;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Worlds;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.worldlayout.AlphaCivWorldLayout;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestAlphaCivWorldLayout {

	private WorldLayoutStrategy worldLayoutStrategy;
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();

	@Before
	public void SetUp(){
		//Here we can change the parameter, to test controlled if the strategy does the required behavior
		worldLayoutStrategy = new AlphaCivWorldLayout(Worlds.WORLD_ALPHA);
		worldLayoutStrategy.putCities(cities);
	}

	@Test
	public void shouldHaveOceans(){
		Utility.shouldHaveTileatXY(worldLayoutStrategy, GameConstants.OCEANS, new Position(0,1));
	}

	@Test
	public void shouldHaveHills(){
		Utility.shouldHaveTileatXY(worldLayoutStrategy, GameConstants.HILLS, new Position(1,0));
	}

	@Test
	public void shouldHaveMountains(){
		Utility.shouldHaveTileatXY(worldLayoutStrategy, GameConstants.MOUNTAINS, new Position(2,2));
	}

	@Test
	public void ThereShouldBeARedCityAt(){
		Utility.thereShouldBeCityAtXY(cities, Player.RED, new Position(1,1));
	}

	@Test
	public void ThereShouldABlueCityAt(){
		Utility.thereShouldBeCityAtXY(cities, Player.BLUE, new Position(4,1));
	}
}
