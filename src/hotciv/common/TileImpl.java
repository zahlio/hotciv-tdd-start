package hotciv.common;

import hotciv.framework.common.Tile;

public class TileImpl implements Tile {
	
	private String type;
	
	public TileImpl(String type){
			this.type = type;
	}

	public String getTypeString() {
		return type;
	}
}