package hotciv.variants;

import hotciv.common.GameImpl;
import hotciv.framework.AttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrategy;

public class EpsilonWinCondition implements WinStrategy {

	//JEG SKAL HAVE EPSILON ATTACKING MED FORDI DEN HAR 
	
	@Override
	public Player getWinner(Game game) {
		return getAttacks(((GameImpl)game).getAttackStrategy());
	}

	public Player getAttacks(AttackStrategy attackStrat){
		if(((EpsilonAttacking)attackStrat).getRedAttacks()==3){
			return Player.RED;
		}else if(((EpsilonAttacking)attackStrat).getBlueAttacks()==3){
			return Player.BLUE;
		}
		return null;
	}
}
