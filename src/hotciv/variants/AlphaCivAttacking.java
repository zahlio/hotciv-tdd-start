package hotciv.variants;

import hotciv.framework.AttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class AlphaCivAttacking implements AttackStrategy {
	
	private int redAttacks = 0;
	private int blueAttacks = 0;

	public int performAttack(Game game, Position from, Position to) {
		if(game.getUnitAt(to).getOwner()==Player.RED){
			redAttacks++;
		}else{
			blueAttacks++;
		}
		return 1;
	}

	@Override
	public int getRedAttacks() {
		return redAttacks;
	}

	@Override
	public int getBlueAttacks() {
		return blueAttacks;
	}

}
