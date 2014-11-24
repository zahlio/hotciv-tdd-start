package hotciv.variants;

import hotciv.framework.AttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class AlphaCivAttacking implements AttackStrategy {
	
	public boolean performAttack(Game game, Position from, Position to) {
		return true;
	}
}
