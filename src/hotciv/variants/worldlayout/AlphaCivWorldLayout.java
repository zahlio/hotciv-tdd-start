package hotciv.variants.worldlayout;

import hotciv.common.CityImpl;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Worlds;
import hotciv.framework.strategy.WorldLayoutStrategy;

import java.util.HashMap;
import java.util.Map;

public class AlphaCivWorldLayout implements WorldLayoutStrategy {

	private Map<Position,Tile> world; 

	public AlphaCivWorldLayout(String[] layout){
		world = Worlds.defineWorld(layout);
	}

	public void putCities(HashMap<Position, CityImpl> cities) {
		cities.put(new Position(1,1), new CityImpl(Player.RED));
		cities.put(new Position(4,1), new CityImpl(Player.BLUE));
	}

	public Tile getTileAt(Position p) {
		return world.get(p);
	}
}
