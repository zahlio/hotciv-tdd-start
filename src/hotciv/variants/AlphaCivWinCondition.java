package hotciv.variants;

import hotciv.framework.common.Game;
import hotciv.framework.common.Player;
import hotciv.framework.strategy.WinStrategy;

public class AlphaCivWinCondition implements WinStrategy {
	
	private int redAttacks = 0;
	private int blueAttacks = 0;
	
	public Player getWinner(Game game){
		if(game.getAge() >= -3000){
			return Player.RED;
		}else{
			return null;
		}
	}

	//purely for statistical use
	public void setAttackCount(Game game) {
		if(game.getPlayerInTurn()==Player.RED){
			redAttacks++;
		}else{
			blueAttacks++;
		}
	}
	
	public int getRedAttacks(){
		return redAttacks;
	}
	
	public int getBlueAttacks(){
		return blueAttacks;
	}
}

