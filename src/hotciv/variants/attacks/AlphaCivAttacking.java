package hotciv.variants.attacks;

import hotciv.framework.common.Game;
import hotciv.framework.common.Position;
import hotciv.framework.strategy.AttackStrategy;

public class AlphaCivAttacking implements AttackStrategy {
	
	public boolean performAttack(Game game, Position from, Position to) {
		return true;
	}
}
