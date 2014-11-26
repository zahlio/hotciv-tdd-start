package hotciv.variants.unitaction;

import hotciv.common.UnitImpl;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Position;
import hotciv.framework.strategy.UnitActionStrategy;

public class SemiCivUnitAction implements UnitActionStrategy {
	SettlerUnitAction settler = new SettlerUnitAction();

	public void performUnitAction(Position p, Game game) {
		UnitImpl u = (UnitImpl) game.getUnitAt(p);

		if(u.getAction(u.getTypeString()).equals(GameConstants.BUILDCITY)){
			settler.performUnitAction(p, game);
		}
	}
}
