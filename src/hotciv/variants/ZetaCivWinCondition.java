package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrategy;

public class ZetaCivWinCondition implements WinStrategy {
	
	BetaCivWinCondition betaWin = new BetaCivWinCondition();
	EpsilonWinCondition epsilonWin = new EpsilonWinCondition();

	@Override
	public Player getWinner(Game game) {
		if(game.getAge()<-2000){
			return betaWin.getWinner(game);
		}else{
			return epsilonWin.getWinner(game);
			//EPSILONCIV ATTACK SHOULD START COUNTING
		}
	}

}
