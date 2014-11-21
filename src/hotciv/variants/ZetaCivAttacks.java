package hotciv.variants;

import hotciv.framework.AttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class ZetaCivAttacks implements AttackStrategy {
	
	boolean played20Rounds = false;

	private AlphaCivAttacking alphaAttack = new AlphaCivAttacking();
	private EpsilonAttacking epsilonAttack = new EpsilonAttacking();
	
	public int performAttack(Game game, Position from, Position to) {
		if(game.getAge()<-2000){
			return alphaAttack.performAttack(game, from, to);
		}else if(game.getAge()>=-2000){
			played20Rounds = true;
			return epsilonAttack.performAttack(game, from, to);
		}
		return 0;
	}

	public int getRedAttacks() {
		if(!played20Rounds){
			return alphaAttack.getRedAttacks();
		}else{
			return epsilonAttack.getRedAttacks();
		}
	}

	public int getBlueAttacks() {
		if(!played20Rounds){
			return alphaAttack.getBlueAttacks();
		}else{
			return epsilonAttack.getBlueAttacks();
		}
	}
	
	public int getRedTotalAttacks(){
		return alphaAttack.getRedAttacks() + epsilonAttack.getRedAttacks();
	}
	
	public int getBlueTotalAttacks(){
		return alphaAttack.getBlueAttacks() + epsilonAttack.getBlueAttacks();
	}
	
}
