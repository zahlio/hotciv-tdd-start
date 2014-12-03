package hotciv.variants.unitaction;

import hotciv.common.CityImpl;
import hotciv.framework.ThetaCivUnit;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Position;
import hotciv.framework.common.Unit;
import hotciv.framework.strategy.UnitActionStrategy;

public class GammaCivUnitAction implements UnitActionStrategy {
	public void performUnitAction(Position p, Game game) {
		if(game.getUnitAt(p).getTypeString().equals(GameConstants.SETTLER)){
			Unit u = game.getUnitAt(p);
			game.getCities().put(p, new CityImpl(u.getOwner()));
			game.getUnits().remove(p);
		}else if(game.getUnitAt(p).getTypeString().equals(GameConstants.ARCHER) || game.getUnitAt(p).getTypeString().equals(ThetaCivUnit.CHARIOT)){
			Unit u = game.getUnitAt(p);
			u.setSkillInUse();
		}
	}
}

