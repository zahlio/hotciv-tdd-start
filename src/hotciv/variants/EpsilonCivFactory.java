package hotciv.variants;

import hotciv.framework.AttackStrategy;
import hotciv.framework.WinAndFightingFactory;
import hotciv.framework.WinStrategy;

public class EpsilonCivFactory implements WinAndFightingFactory {

	@Override
	public WinStrategy createWinner() {
		return new EpsilonWinCondition();
	}

	@Override
	public AttackStrategy createAttack() {
		return new EpsilonAttacking();
	}

}
