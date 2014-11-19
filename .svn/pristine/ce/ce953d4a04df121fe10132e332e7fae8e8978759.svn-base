package hotciv.variants;

import hotciv.framework.AttackStrategy;
import hotciv.framework.WinAndFightingFactory;
import hotciv.framework.WinStrategy;

public class AlphaCivFactory implements WinAndFightingFactory {

	public WinStrategy createWinner() {
		return new AlphaCivWinCondition();
	}

	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}

}
