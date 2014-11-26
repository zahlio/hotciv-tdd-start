package hotciv.variants.unitaction;

import hotciv.common.UnitImpl;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Position;
import hotciv.framework.strategy.UnitActionStrategy;

public class GammaCivUnitAction implements UnitActionStrategy{
	
	ArcherUnitAction archer = new ArcherUnitAction();
	SettlerUnitAction settler = new SettlerUnitAction();

	public void performUnitAction(Position p, Game game) {
		UnitImpl u = (UnitImpl) game.getUnitAt(p);
		
		if(u.getAction(u.getTypeString()).equals(GameConstants.BUILDCITY)){
			settler.performUnitAction(p, game);
		}else if(u.getAction(u.getTypeString()).equals(GameConstants.FORTIFY)){
			archer.performUnitAction(p, game);
		}
	}
}
