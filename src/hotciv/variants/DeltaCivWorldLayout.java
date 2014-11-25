package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.common.TileImpl;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Tile;
import hotciv.framework.strategy.WorldLayoutStrategy;

import java.util.HashMap;
import java.util.Map;

public class DeltaCivWorldLayout implements WorldLayoutStrategy {

	private Map<Position,Tile> world; 

	public DeltaCivWorldLayout(String[] layout) {
		world = defineWorld(layout);
	}
	
	public void putCities(HashMap<Position, CityImpl> cities) {
		cities.put(new Position(8,12), new CityImpl(Player.RED));
		cities.put(new Position(4,5), new CityImpl(Player.BLUE));
	}
	
	public Tile getTileAt( Position p ) { return world.get(p); }

	/** Define the world as the DeltaCiv layout */
	public Map<Position,Tile> defineWorld(String[] layout) {
		
		// Conversion...
		Map<Position,Tile> theWorld = new HashMap<Position,Tile>();
		String line;
		for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
			line = layout[r];
			for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
				char tileChar = line.charAt(c);
				String type = "error";
				if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
				if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
				if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
				if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
				if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
				Position p = new Position(r,c);
				theWorld.put( p, new TileImpl(type));
			}
		}
		return theWorld;
	}
}
