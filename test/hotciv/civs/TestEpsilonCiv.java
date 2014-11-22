package hotciv.civs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.factories.EpsilonCivFactory;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;

import org.junit.Before;
import org.junit.Test;

//THIS METHOD NEEDS TO TEST ATTACKING ASWELL
public class TestEpsilonCiv {

	Game game;
	@Before 
	public void setUp() {
		game = new GameImpl(new EpsilonCivFactory());
	}

	@Test
	public void RedShouldBeWinnerIfHeWins3Times(){
		
		game.getUnits().put(new Position(9,0), new UnitImpl(GameConstants.LEGION, Player.RED));
		game.getUnits().put(new Position(9,1), new UnitImpl(GameConstants.LEGION, Player.BLUE));
		game.getUnits().put(new Position(9,3), new UnitImpl(GameConstants.LEGION, Player.BLUE));
		game.getUnits().put(new Position(9,5), new UnitImpl(GameConstants.LEGION, Player.BLUE));

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
}
