package hotciv.variants.worldlayout;

import hotciv.common.CityImpl;
import hotciv.common.TileImpl;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.strategy.WorldLayoutStrategy;

import java.util.HashMap;

import thirdparty.ThirdPartyFractalGenerator;

public class FractalAdapter implements WorldLayoutStrategy {

	ThirdPartyFractalGenerator fractalMap;
	
	public FractalAdapter() {
		fractalMap = new ThirdPartyFractalGenerator();
	}

	public void putCities(HashMap<Position, CityImpl> cities) {
	}

	public Tile getTileAt(Position p) {
		char tileChar = fractalMap.getLandscapeAt(p.getRow(), p.getColumn());
		if ( tileChar == '.' ) { return new TileImpl(GameConstants.OCEANS); }
		if ( tileChar == 'o' ) { return new TileImpl(GameConstants.PLAINS); }
		if ( tileChar == 'M' ) { return new TileImpl(GameConstants.MOUNTAINS); }
		if ( tileChar == 'f' ) { return new TileImpl(GameConstants.FOREST); }
		if ( tileChar == 'h' ) { return new TileImpl(GameConstants.HILLS); }
		return null;
	}
}

