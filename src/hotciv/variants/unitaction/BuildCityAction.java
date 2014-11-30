package hotciv.variants.unitaction;

import hotciv.common.CityImpl;
import hotciv.framework.common.Game;
import hotciv.framework.common.Position;
import hotciv.framework.common.Unit;
import hotciv.framework.strategy.UnitActionPattern;

public class BuildCityAction implements UnitActionPattern {

	public void performUnitAction(Position p, Game game) {
		Unit u = game.getUnitAt(p);
		game.getCities().put(p, new CityImpl(u.getOwner()));
		game.getUnits().remove(p);
	}
}
