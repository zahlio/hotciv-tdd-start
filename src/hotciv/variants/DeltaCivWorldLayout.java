package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.common.TileImpl;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.WorldLayoutStrategy;

import java.util.HashMap;
import java.util.Map;

public class DeltaCivWorldLayout implements WorldLayoutStrategy {

	private Map<Position,Tile> world; 

	public DeltaCivWorldLayout() {
		world = defineWorld();
	}
	
	public void putCities(HashMap<Position, CityImpl> cities) {
		cities.put(new Position(8,12), new CityImpl(Player.RED));
		cities.put(new Position(4,5), new CityImpl(Player.BLUE));
	}
	
	public Tile getTileAt( Position p ) { return world.get(p); }

	/** Define the world as the DeltaCiv layout */
	private Map<Position,Tile> defineWorld() {
		// Basically we use a 'data driven' approach - code the
		// layout in a simple semi-visual representation, and
		// convert it to the actual Game representation.
		//This is a 16x16 version of the world
		String[] layout =
				new String[] {
				"...ooMooooo.....",
				"..ohhoooofffoo..",
				".oooooMooo...oo.",
				".ooMMMoooo..oooo",
				"...ofooohhoooo..",
				".ofoofooooohhoo.",
				"...ooo..........",
				".ooooo.ooohooM..",
				".ooooo.oohooof..",
				"offfoooo.offoooo",
				"oooooooo...ooooo",
				".ooMMMoooo......",
				"..ooooooffoooo..",
				"....ooooooooo...",
				"..ooohhoo.......",
				".....ooooooooo..",
		};
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
