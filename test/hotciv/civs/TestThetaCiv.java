package hotciv.civs;

import static org.junit.Assert.assertEquals;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.factories.ThetaCivFactory;
import hotciv.framework.common.Game;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Unit;
import hotciv.throwable.NotAUnitException;
import hotciv.variants.unitimpl.ThetaCivUnitImpl;
import hotciv.variants.units.ThetaCivUnits;

import org.junit.Before;
import org.junit.Test;

public class TestThetaCiv {

	private Game game;
	
	@Before
	public void SetUp(){
		game = new GameImpl(new ThetaCivFactory("ThetaCiv.txt"));
		game.getUnits().put(new Position(3,3), new UnitImpl(new ThetaCivUnitImpl(ThetaCivUnits.CHARIOT, Player.RED)));
	}
	
	@Test
	public void ChariotShouldHaveBeenProduced() throws NotAUnitException{
		game.changeProductionInCityAt(new Position(1,1), ThetaCivUnits.CHARIOT);
		Utility.playRounds(game, 4);
		Unit u = game.getUnitAt(new Position(1,1));
		assertEquals("There should be a Chariot on (1,1)", ThetaCivUnits.CHARIOT, u.getTypeString());
	}

	@Test
	public void ChariotShouldBeFortified(){
		game.performUnitActionAt(new Position(3,3));
		assertEquals("Chariot should have 2 defence", 2, game.getUnitAt(new Position(3,3)).getDefensiveStrength());
		assertEquals("Chariot should have 0 moveCount", 0, game.getUnitAt(new Position(3,3)).getMoveCount());
	}
}