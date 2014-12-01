package hotciv.variants.units;

import hotciv.common.UnitImpl;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Unit;
import hotciv.framework.strategy.UnitActionPattern;
import hotciv.framework.strategy.UnitStrategy;
import hotciv.variants.unitaction.BuildCityAction;
import hotciv.variants.unitaction.FortifyAction;
import hotciv.variants.unitimpl.GammaCivUnitImpl;

public class GammaCivUnits implements UnitStrategy {

	public Unit produceUnit(String unitType, Player p) {
		return new UnitImpl(new GammaCivUnitImpl(unitType, p));
	}

	public void performUnitAction(Game game, Position p) {
		UnitActionPattern fortify = new FortifyAction();
		UnitActionPattern buildCity = new BuildCityAction();

		Unit u =  game.getUnitAt(p);

		if(u.getTypeString().equals(GameConstants.SETTLER)){
			buildCity.performUnitAction(p, game);
		}else if(u.getTypeString().equals(GameConstants.ARCHER)){
			fortify.performUnitAction(p, game);
		}
	}

	public boolean hasUnit(String unitType) {
		if(unitType==GameConstants.ARCHER || unitType==GameConstants.LEGION || unitType==GameConstants.SETTLER){
			return true;
		}else{
			return false;
		}
	}
}
