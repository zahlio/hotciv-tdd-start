package hotciv.variants;

import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackStrategy;
import hotciv.framework.CivFactory;
import hotciv.framework.UnitActionStrategy;
import hotciv.framework.WinStrategy;
import hotciv.framework.WorldLayoutStrategy;

public class DeltaCivFactory implements CivFactory {

	public AgingStrategy createAging() {
		return new AlphaCivAging();
	}
	
	public UnitActionStrategy createUnitAction() {
		return new AlphaCivUnitAction();
	}

	public WorldLayoutStrategy createLayout() {
		return new DeltaCivWorldLayout();
	}

	@Override
	public WinStrategy createWinner() {
		return new AlphaCivWinCondition();
	}

	@Override
	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}
}
