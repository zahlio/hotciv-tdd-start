package hotciv.variants;

import hotciv.framework.AttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class AlphaCivAttacking implements AttackStrategy {
	
	public int performAttack(Game game, Position from, Position to) {
		return 1;
	}
}
