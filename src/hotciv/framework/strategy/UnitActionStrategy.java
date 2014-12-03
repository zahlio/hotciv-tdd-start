package hotciv.framework.strategy;

import hotciv.framework.common.Game;
import hotciv.framework.common.Position;

//FIND OUT WHAT PATTERN THIS IS
public interface UnitActionStrategy {

	/**
	 * this method performs the units action
	 * @param p perform action at this position
	 * @param game write 'this' in here, needs the game as parameter to figure out it's action
	 */
	public void performUnitAction(Position p, Game game);
}
