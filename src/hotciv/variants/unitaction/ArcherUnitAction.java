package hotciv.variants.unitaction;

import hotciv.common.UnitImpl;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Position;
import hotciv.framework.strategy.UnitActionStrategy;

public class ArcherUnitAction implements UnitActionStrategy {

	public void performUnitAction(Position p, Game game) {
		UnitImpl u = (UnitImpl) game.getUnitAt(p);
		u.setSkillInUse(GameConstants.FORTIFY);
		
	}

}
