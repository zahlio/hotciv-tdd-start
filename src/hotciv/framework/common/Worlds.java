package hotciv.framework.common;

import hotciv.common.TileImpl;

import java.util.HashMap;
import java.util.Map;

//JAVADOC
public class Worlds {
	
	public static Map<Position, Tile> defineWorld(String[] layout) {
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

	// DeltaCiv
	public static final String[] WORLD_DELTA = new String[] {
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
	
	//Alphaciv
	public static final String[] WORLD_ALPHA = new String[] {
		"o.oooooooooooooo",
		"hooooooooooooooo",
		"ooMoooooooooooooo",
		".ooooooooooooooo",
		"oooooooooooooooo",
		"oooooooooooooooo",
		"oooooooooooooooo",
		".ooooooooooooooo",
		"ooooooooohoooooo",
		"oooooooooooooooo",
		"oooooooooooooooo",
		"oooooooooooooooo",
		"oooooooooooooooo",
		"oooooooooooooooo",
		"oooooooooooooooo",
		"oooooooooooooooo",
	};
}
