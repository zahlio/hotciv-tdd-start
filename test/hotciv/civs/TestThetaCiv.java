package hotciv.civs;

import static org.junit.Assert.assertEquals;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.common.UnitInfo;
import hotciv.factories.ThetaCivFactory;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.ThetaCivUnit;
import hotciv.framework.Unit;
import hotciv.throwable.NotAUnitException;

import org.junit.Before;
import org.junit.Test;

public class TestThetaCiv {

	private Game game;
	
	@Before
	public void SetUp(){
		game = new GameImpl(new ThetaCivFactory());
		game.getUnits().put(new Position(3,3), new UnitImpl(new UnitInfo(20,1,2),ThetaCivUnit.CHARIOT, Player.RED));
	}
	
	@Test
	public void ChariotShouldHaveBeenProduced() throws NotAUnitException{
		game.changeProductionInCityAt(new Position(1,1), ThetaCivUnit.CHARIOT);
		Utility.playRounds(game, 4);
		Unit u = game.getUnitAt(new Position(1,1));
		assertEquals("There should be a Chariot on (1,1)", ThetaCivUnit.CHARIOT, u.getTypeString());
	}

	@Test
	public void ChariotShouldBeFortified(){
		game.performUnitActionAt(new Position(3,3));
		assertEquals("Chariot should have 2 defence", 2, game.getUnitAt(new Position(3,3)).getDefensiveStrength());
		assertEquals("Chariot should have 0 moveCount", 0, game.getUnitAt(new Position(3,3)).getMoveCount());
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
	
	
	@Test
	public void legionShouldNotHavePerformedAnAction(){
		game.performUnitActionAt(new Position(3,2));
		UnitImpl u = (UnitImpl) game.getUnitAt(new Position(3,2));
		assertEquals("Legion", false, u.getIsSkillInUse());
	}
}
