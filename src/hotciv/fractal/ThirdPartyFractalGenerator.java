package hotciv.fractal;

import java.util.Map;

import hotciv.framework.common.Position;
import hotciv.framework.common.Tile;
import hotciv.framework.common.Worlds;

//TODO: Finish this class, dunno if i should make it
public class ThirdPartyFractalGenerator {

	private Map<Position,Tile> landscape; 
	
	public ThirdPartyFractalGenerator(){
		landscape = Worlds.defineWorld(Worlds.WORLD_ALPHA);
	}

	public char getLandscapeAt ( int row , int column ) {
		return 0;
	}
}