package hotciv.variants;

import hotciv.framework.common.Game;
import hotciv.framework.common.Player;
import hotciv.framework.strategy.WinStrategy;

public class EpsilonWinCondition implements WinStrategy {

	private int redAttacks = 0;
	private int blueAttacks = 0;

	public Player getWinner(Game game) {
		if(redAttacks==3){
			return Player.RED;
		}else if(blueAttacks==3){
			return Player.BLUE;
		}
		return null;
	}

	public void setAttackCount(Game game) {
		if(game.getPlayerInTurn()==Player.RED){
			redAttacks++;
		}else{
			blueAttacks++;
		}
	}

	//purely for statistical use
	public int getRedAttacks(){
		return redAttacks;
	}

	public int getBlueAttacks(){
		return blueAttacks;
	}
}
