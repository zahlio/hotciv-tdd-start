package hotciv.framework.strategy;

import hotciv.framework.common.Game;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Unit;

public interface UnitStrategy {
	
	public Unit produceUnit(String unitType, Player p);
	
	public void performUnitAction(Game game, Position p);
	
	public boolean hasUnit(String unitType);
}
