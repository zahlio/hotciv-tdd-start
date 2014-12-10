package hotciv.variants.unitaction;

import hotciv.common.CityImpl;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.strategy.UnitActionStrategy;

public class SemiCivUnitAction implements UnitActionStrategy {

	public void performUnitAction(Position p, Game game) {
		if(game.getUnitAt(p).getTypeString().equals(GameConstants.SETTLER)){
			Unit u = game.getUnitAt(p);
			game.getCities().put(p, new CityImpl(u.getOwner()));
			game.getUnits().remove(p);
		}
	}
}

