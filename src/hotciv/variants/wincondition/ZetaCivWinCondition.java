package hotciv.variants.wincondition;

import hotciv.framework.common.Game;
import hotciv.framework.common.Player;
import hotciv.framework.strategy.WinStrategy;

public class ZetaCivWinCondition implements WinStrategy {

	private int totalRedAttacks;
	private int totalBlueAttacks;
	
	WinStrategy winStrat1;
	WinStrategy winStrat2;
	
	public ZetaCivWinCondition(WinStrategy winStrat1, WinStrategy winStrat2) {
		this.winStrat1 = winStrat1;
		this.winStrat2 = winStrat2;
	}

	
//NOT DONE
	public Player getWinner(Game game) {
		if(game.getAge()<-2000){
			return winStrat1.getWinner(game);
		}else{
			return winStrat2.getWinner(game);
		}
	}

	//purely for statistical use
	public void setAttackCount(Game game) {
		if(game.getAge()<-2000){
			winStrat1.setAttackCount(game);
		}else if(game.getAge()>=-2000){
			winStrat2.setAttackCount(game);
		}
		
		if(game.getPlayerInTurn()==Player.RED){
			totalRedAttacks++;
		}else if(game.getPlayerInTurn()==Player.BLUE){
			totalBlueAttacks++;
		}
	}

	public int getRedAttacks(){
		return totalRedAttacks;
	}

	public int getBlueAttacks(){
		return totalBlueAttacks;
	}
}
