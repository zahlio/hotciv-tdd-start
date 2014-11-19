package hotciv.variants;

import hotciv.common.UnitImpl;
import hotciv.framework.AttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class AlphaCivAttacking implements AttackStrategy {

	public int performAttack(Game game, Position from, Position to) {
		game.getUnits().remove(to);
		game.getUnits().put(to, (UnitImpl) game.getUnitAt(from));
		game.getUnits().remove(from);
		return 1;
	}

}
