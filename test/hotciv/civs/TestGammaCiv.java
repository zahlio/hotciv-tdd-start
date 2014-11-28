package hotciv.civs;

import static org.junit.Assert.assertEquals;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.factories.GammaCivFactory;
import hotciv.framework.common.Game;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;

import org.junit.Before;
import org.junit.Test;

public class TestGammaCiv {

	private Game game;
	/** Fixture for alphaciv testing. */
	@Before
	public void setUp() {
		game = new GameImpl(new GammaCivFactory());
	}
	
	@Test
	public void settlerShouldHaveCreatedACityAt4_3(){
		game.performUnitActionAt(new Position(4,3));
		settlerShouldHaveCreatedACityAt(4, 3, Player.RED);
	}
	
	public void settlerShouldHaveCreatedACityAt(int x, int y, Player p){
		assertEquals(
				String.format("There should be a city at (%d, %d) owned by player %s", x, y, p), 
				p, 
				game.getCityAt(new Position(x, y)).getOwner()
		);
	}
	
	@Test
	public void ArcherShouldBeFortified(){
		game.performUnitActionAt(new Position(2,0));
		UnitImpl u = (UnitImpl) game.getUnitAt(new Position(2, 0));
		assertEquals("Archers Defensive strength should now be 6", 6 ,u.getDefensiveStrength());
		game.performUnitActionAt(new Position(2,0));
		assertEquals("Archers Defensive strength should now be 3", 3 ,u.getDefensiveStrength());
	}
	
	@Test
	public void ArcherMoveCountShouldBe0(){
		game.performUnitActionAt(new Position(2, 0));
		UnitImpl u = (UnitImpl) game.getUnitAt(new Position(2, 0));
		assertEquals("Archer Should have 0 movecount", 0 ,u.getMoveCount());
	}
}
