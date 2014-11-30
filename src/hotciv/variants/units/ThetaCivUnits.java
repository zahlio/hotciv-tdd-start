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
import hotciv.variants.unitimpl.ThetaCivUnitImpl;

public class ThetaCivUnits implements UnitStrategy {

	public static final String CHARIOT = "chariot";
	
	public UnitImpl produceUnit(String unitType, Player p){
		return new UnitImpl(new ThetaCivUnitImpl(unitType, p));
	}

	public void performUnitAction(Game game, Position p) {
		UnitActionPattern fortiy = new FortifyAction();
		UnitActionPattern buildCity = new BuildCityAction();

		Unit u = game.getUnitAt(p);

		if(u.getTypeString().equals(GameConstants.SETTLER)){
			buildCity.performUnitAction(p, game);
		}else if(u.getTypeString().equals(GameConstants.ARCHER) || u.getTypeString().equals(CHARIOT)){
			fortiy.performUnitAction(p, game);
		}
	}

	public boolean hasUnit(String unitType) {
		if(unitType==GameConstants.ARCHER || unitType==GameConstants.LEGION || 
				unitType==GameConstants.SETTLER || unitType==CHARIOT){
			return true;
		}else{
			return false;
		}
	}
}
