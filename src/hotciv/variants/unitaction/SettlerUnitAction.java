package hotciv.variants.unitaction;

import hotciv.common.CityImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.common.Game;
import hotciv.framework.common.Position;
import hotciv.framework.strategy.UnitActionStrategy;

public class SettlerUnitAction implements UnitActionStrategy {

	public void performUnitAction(Position p, Game game) {
		UnitImpl u = (UnitImpl) game.getUnitAt(p);
		game.getCities().put(p, new CityImpl(u.getOwner()));
		game.getUnits().remove(p);
	}
}
