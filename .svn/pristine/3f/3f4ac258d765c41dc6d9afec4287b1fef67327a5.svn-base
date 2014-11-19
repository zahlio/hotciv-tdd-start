package hotciv.variants;

import hotciv.framework.AttackStrategy;
import hotciv.framework.WinAndFightingFactory;
import hotciv.framework.WinStrategy;

public class BetaCivFactory implements WinAndFightingFactory{

	public WinStrategy createWinner() {
		return new BetaCivWinCondition();
	}

	@Override
	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}

}
