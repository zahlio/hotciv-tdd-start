package hotciv.variants.attacks;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.strategy.AttackStrategy;

public class AlphaCivAttacking implements AttackStrategy {
	
	public boolean performAttack(Game game, Position from, Position to) {
		return true;
	}
}
