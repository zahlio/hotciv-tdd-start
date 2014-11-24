package hotciv.variants;

import hotciv.framework.common.Game;
import hotciv.framework.common.Position;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.DieStrategy;

public class ZetaCivAttacks implements AttackStrategy {
	
	private EpsilonAttacking epsilonAttack;
	private AlphaCivAttacking alphaAttack;
	
	public ZetaCivAttacks(DieStrategy die) {
		epsilonAttack  = new EpsilonAttacking(die);
		alphaAttack = new AlphaCivAttacking();
	}
	
	public boolean performAttack(Game game, Position from, Position to) {
		if(game.getAge()<-2000){
			return alphaAttack.performAttack(game, from, to);
		}else{
			return epsilonAttack.performAttack(game, from, to);
		}
	}
}
