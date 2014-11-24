package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Position;
import hotciv.framework.strategy.UnitActionStrategy;

public class GammaCivUnitAction implements UnitActionStrategy{

	public void performUnitAction(Position p, Game game) {
		UnitImpl u = (UnitImpl) game.getUnitAt(p);
		
		if(u.getAction(u.getTypeString()).equals(GameConstants.BUILDCITY)){
			game.getCities().put(p, new CityImpl(u.getOwner()));
			game.getUnits().remove(p);
		}else if(u.getAction(u.getTypeString()).equals(GameConstants.FORTIFY)){
			u.setSkillInUse(GameConstants.FORTIFY);
		}
	}

}
