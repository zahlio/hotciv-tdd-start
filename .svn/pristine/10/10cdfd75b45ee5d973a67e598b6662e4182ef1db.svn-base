package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.common.TileImpl;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.WorldLayoutStrategy;

import java.util.HashMap;

public class AlphaCivWorldLayout implements WorldLayoutStrategy {
		
	public void putCities(HashMap<Position, CityImpl> cities) {
		cities.put(new Position(1,1), new CityImpl(Player.RED));
		cities.put(new Position(4,1), new CityImpl(Player.BLUE));
	}

	public Tile getTileAt(Position p) {
		Tile t = null;
			if(p.getRow()==0 && p.getColumn()==1){
				t= new TileImpl(GameConstants.OCEANS);
			}else if(p.getRow()==1 && p.getColumn()==0){
				t = new TileImpl(GameConstants.HILLS);
			}else if(p.getRow()==2 && p.getColumn()==2 ){
				t = new TileImpl(GameConstants.MOUNTAINS);
			}else{
				t = new TileImpl(GameConstants.PLAINS);
		}
		return t;
	}
}
