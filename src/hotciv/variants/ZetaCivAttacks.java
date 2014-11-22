package hotciv.variants;

import hotciv.framework.AttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class ZetaCivAttacks implements AttackStrategy {
	
	private AlphaCivAttacking alphaAttack = new AlphaCivAttacking();
	private EpsilonAttacking epsilonAttack = new EpsilonAttacking();
	
	public int performAttack(Game game, Position from, Position to) {
		if(game.getAge()<-2000){
			return alphaAttack.performAttack(game, from, to);
		}else if(game.getAge()>=-2000){
			return epsilonAttack.performAttack(game, from, to);
		}
		return 0;
	}
}
