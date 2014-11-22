package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrategy;

public class ZetaCivWinCondition implements WinStrategy {

	private int totalRedAttacks;
	private int totalBlueAttacks;

	BetaCivWinCondition betaWin = new BetaCivWinCondition();
	EpsilonWinCondition epsilonWin = new EpsilonWinCondition();

	public Player getWinner(Game game) {
		if(game.getAge()<-2000){
			return betaWin.getWinner(game);
		}else{
			return epsilonWin.getWinner(game);
		}
	}

	//purely for statistical use
	public void setAttackCount(Game game) {
		if(game.getAge()<-2000){
			betaWin.setAttackCount(game);
		}else if(game.getAge()>=-2000){
			epsilonWin.setAttackCount(game);
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
