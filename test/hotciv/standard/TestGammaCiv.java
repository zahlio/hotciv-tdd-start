package hotciv.standard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hotciv.common.GameImpl;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.variants.AlphaCivAging;
import hotciv.variants.AlphaCivFactory;
import hotciv.variants.AlphaCivWorldLayout;
import hotciv.variants.GammaCivUnitAction;

import org.junit.Before;
import org.junit.Test;

public class TestGammaCiv {

	private Game game;
	/** Fixture for alphaciv testing. */
	@Before
	public void setUp() {
		game = new GameImpl(new AlphaCivAging(), new GammaCivUnitAction(), new AlphaCivWorldLayout(), new AlphaCivFactory());
	}
	
	@Test
	public void settlerShouldHaveCreatedACityAt4_3(){
		game.performUnitActionAt(new Position(4,3));
		settlerShouldHaveCreatedACityAt(4, 3);
	}
	
	@Test
	public void settlerShouldHaveCreatedACityAt(int x, int y){
		assertNull(String.format("There should be a city at %d, %d owned by player Red", x, y), game.getCityAt(new Position(x, y)));
	}
	
	@Test
	public void ArcherShouldBeFortified(){
		game.performUnitActionAt(new Position(2,0));
		Unit u = game.getUnitAt(new Position(2, 0));
		assertEquals("Archers Defensive strength should now be 6", 6 ,u.getDefensiveStrength());
		game.performUnitActionAt(new Position(2,0));
		assertEquals("Archers Defensive strength should now be 3", 3 ,u.getDefensiveStrength());
	}
	
	@Test
	public void ArcherMoveCountShouldBe0(){
		game.performUnitActionAt(new Position(2, 0));
		Unit u = game.getUnitAt(new Position(2, 0));
		assertEquals("Archer Should have 0 movecount", 0 ,u.getMoveCount());
	}
}
