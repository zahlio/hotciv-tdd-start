package hotciv.variants.unitaction;

import hotciv.common.UnitImpl;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Position;
import hotciv.framework.strategy.UnitActionStrategy;

public class SemiCivAction implements UnitActionStrategy {
	BuildCityAction settler = new BuildCityAction();

	public void performUnitAction(Position p, Game game) {
		UnitImpl u = (UnitImpl) game.getUnitAt(p);

		if(u.getTypeString().equals(GameConstants.SETTLER)){
			settler.performUnitAction(p, game);
		}
	}
}
