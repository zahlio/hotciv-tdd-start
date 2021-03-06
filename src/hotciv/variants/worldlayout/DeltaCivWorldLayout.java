package hotciv.variants.worldlayout;

import hotciv.common.CityImpl;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Worlds;
import hotciv.framework.strategy.WorldLayoutStrategy;

import java.util.HashMap;
import java.util.Map;

public class DeltaCivWorldLayout implements WorldLayoutStrategy {

	private Map<Position,Tile> world; 

	public DeltaCivWorldLayout(String[] layout) {
		world = Worlds.defineWorld(layout);
	}
	
	public void putCities(HashMap<Position, CityImpl> cities) {
		cities.put(new Position(8,12), new CityImpl(Player.RED));
		cities.put(new Position(4,5), new CityImpl(Player.BLUE));
	}
	
	public Tile getTileAt( Position p ) { return world.get(p); }
}
