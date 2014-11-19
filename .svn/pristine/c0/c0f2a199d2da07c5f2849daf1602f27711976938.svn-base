package hotciv.variants;

import hotciv.common.GameImpl;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrategy;

public class EpsilonWinCondition implements WinStrategy {

	@Override
	public Player getWinner(Game game) {
		if(((GameImpl)game).getRedAttacks()==3){
			return Player.RED;
		}else if(((GameImpl)game).getBlueAttacks()==3){
			return Player.BLUE;
		}else{
			return null;
		}
	}

}
