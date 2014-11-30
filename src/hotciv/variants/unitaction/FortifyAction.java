package hotciv.variants.unitaction;

import hotciv.framework.common.Game;
import hotciv.framework.common.Position;
import hotciv.framework.common.Unit;
import hotciv.framework.strategy.UnitActionPattern;

public class FortifyAction implements UnitActionPattern {

	public void performUnitAction(Position p, Game game) {
		Unit u = game.getUnitAt(p);
		u.setSkillInUse();
	}

}

