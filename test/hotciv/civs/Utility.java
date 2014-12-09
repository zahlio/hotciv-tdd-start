package hotciv.civs;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import hotciv.common.CityImpl;
import hotciv.framework.common.City;
import hotciv.framework.common.Game;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Tile;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;

public class Utility {


	public static void playRounds(Game game, int rounds){
		for(int i=0;i<rounds*2;i++){
			game.endOfTurn();
		}
	}

	public static void changeOwnerOfCity(Game game, Position p){
		CityImpl c = (CityImpl) game.getCityAt(p);
		c.changeOwner();			
	}

	/*
	 * Dynamic test for tile.
	 */
	public static void shouldHaveTileatXY(WorldLayoutStrategy layoutStratgey, String constant, Position p){
		Tile t = layoutStratgey.getTileAt(p);
		assertEquals(
				String.format("Should have %s at (%d, %d)", constant, p.getRow(), p.getColumn()), 
				constant, 
				t.getTypeString()
				);
	}

	/*
	 * Dynamic test for tile.
	 */
	public static void civShouldHaveTileatXY(Game game, String constant, Position p){
		Tile t = game.getTileAt(p);
		assertEquals(
				String.format("Should have %s at (%d, %d)", constant, p.getRow(), p.getColumn()), 
				constant, 
				t.getTypeString()
				);
	}

	/*
	 * Dynamic test for city at position.
	 */
	public static void thereShouldBeCityAtXY(HashMap<Position, CityImpl> cities, Player player, Position p){
		City c = cities.get(p);
		assertEquals(
				String.format(
						"There should be a %s city at (%d,%d)", 
						c, 
						p.getRow(), 
						p.getColumn()
						), 
						player, 
						c.getOwner()
				);
	}

	/*
	 * Dynamic test for city at position.
	 */
	public static void civThereShouldBeCityAtXY(Game game, Player player, Position p){
		City c = game.getCities().get(p);
		assertEquals(
				String.format(
						"There should be a %s city at (%d,%d)", 
						c, 
						p.getRow(), 
						p.getColumn()
						), 
						player, 
						c.getOwner()
				);
	}
	
	public static void attack(Game game, WinStrategy win, int attacks){
		for(int i=0; i<attacks;i++){
			win.setAttackCount(game);
		}
	}
}
