package hotciv.framework.strategy;

import hotciv.common.CityImpl;
import hotciv.framework.Position;
import hotciv.framework.Tile;

import java.util.HashMap;

public interface WorldLayoutStrategy {
	
	/** puts the cities of the world depending on the strategy
	   * @param cities send the city HashMap to populate cities
	   */
	public void putCities(HashMap<Position, CityImpl> cities);
	
	/** return a specific tile.
	   * Precondition: Position p is a valid position in the world.
	   * @param p the position in the world that must be returned.
	   * @return the tile at position p.
	   */
	public Tile getTileAt(Position p);
}
