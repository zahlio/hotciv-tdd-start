package hotciv.variants.units;

import hotciv.common.UnitImpl;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Unit;
import hotciv.framework.strategy.UnitStrategy;
import hotciv.variants.unitimpl.AlphaCivUnitImpl;

public class AlphaCivUnits implements UnitStrategy {

	public Unit produceUnit(String unitType, Player p) {
			return new UnitImpl(new AlphaCivUnitImpl(unitType, p));
	}

	public void performUnitAction(Game game, Position p) {
		//This method is empty, because AlphaCiv does not have UnitAction
	}

	public boolean hasUnit(String unitType) {
		if(unitType==GameConstants.ARCHER || unitType==GameConstants.LEGION || unitType==GameConstants.SETTLER){
			return true;
		}else{
			return false;
		}
	}

}
