package hotciv.variants;

import hotciv.common.GameImpl;
import hotciv.framework.AttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrategy;

public class EpsilonWinCondition implements WinStrategy {

	//KAN IKKE TESTE DET HER FORDI JEG CASTER GAMEIMPL
	
	public Player getWinner(Game game) {
		return giveWinner(((GameImpl)game).getAttackStrategy());
	}
	
	public Player giveWinner(AttackStrategy attackStrategy){
		if(attackStrategy.getRedAttacks()==3){
			return Player.RED;
		}else if(attackStrategy.getBlueAttacks()==3){
			return Player.BLUE;
		}
		return null;
	}
}
