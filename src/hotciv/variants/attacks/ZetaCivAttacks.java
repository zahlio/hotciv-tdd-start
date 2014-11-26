package hotciv.variants.attacks;

import hotciv.framework.common.Game;
import hotciv.framework.common.Position;
import hotciv.framework.strategy.AttackStrategy;

public class ZetaCivAttacks implements AttackStrategy {
	
	AttackStrategy attackStrategy1; 
	AttackStrategy attackStrategy2;
	
	public ZetaCivAttacks(AttackStrategy attackStrategy1, AttackStrategy attackStrategy2) {
		this.attackStrategy1 = attackStrategy1;
		this.attackStrategy2 = attackStrategy2;
	}
	
	public boolean performAttack(Game game, Position from, Position to) {
		if(game.getAge()<-2000){
			return attackStrategy1.performAttack(game, from, to);
		}else{
			return attackStrategy2.performAttack(game, from, to);
		}
	}
}
