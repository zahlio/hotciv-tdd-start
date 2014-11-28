package hotciv.civs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.factories.EpsilonCivFactory;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Unit;
import hotciv.variants.die.OneSidedDie;
import hotciv.variants.unitimpl.AlphaCivUnitImpl;

import org.junit.Before;
import org.junit.Test;

public class TestEpsilonCiv {

	Game game;
	@Before 
	public void setUp() {
		game = new GameImpl(new EpsilonCivFactory(new OneSidedDie()));
	}

	@Test
	public void RedShouldBeWinnerIfHeWins3Times(){
		
		game.getUnits().put(new Position(9,0), new UnitImpl(new AlphaCivUnitImpl(GameConstants.LEGION, Player.RED)));
		game.getUnits().put(new Position(9,1), new UnitImpl(new AlphaCivUnitImpl(GameConstants.LEGION, Player.BLUE)));
		game.getUnits().put(new Position(9,3), new UnitImpl(new AlphaCivUnitImpl(GameConstants.LEGION, Player.BLUE)));
		game.getUnits().put(new Position(9,5), new UnitImpl(new AlphaCivUnitImpl(GameConstants.LEGION, Player.BLUE)));

		game.moveUnit(new Position(9,0), new Position(9,1));
		Utility.playRounds(game,1);
		game.moveUnit(new Position(9,1), new Position(9,2));
		Utility.playRounds(game,1);
		game.moveUnit(new Position(9,2), new Position(9,3));
		Utility.playRounds(game,1);
		game.moveUnit(new Position(9,3), new Position(9,4));
		Utility.playRounds(game,1);
		game.moveUnit(new Position(9,4), new Position(9,5));
		Unit u = game.getUnitAt(new Position(9,5));

		if(u.getOwner()==Player.RED){
			assertEquals("Red should be winner if he succeed all attacks", Player.RED, game.getWinner());
		}else{
			assertNull("There should be no winner", game.getWinner());
		}
	}
	
	@Test
	public void RedLegionShouldHaveKilledBlueArcherAndMovedToPosition(){
		
	}
	
	@Test
	public void BlueArcherShouldRemainInPositionAndRedArcherRemoved(){
		
	}
}
