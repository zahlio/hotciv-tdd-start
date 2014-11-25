package hotciv.factories;

import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitActionStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.AlphaCivAging;
import hotciv.variants.AlphaCivAttacking;
import hotciv.variants.AlphaCivUnitAction;
import hotciv.variants.AlphaCivWinCondition;
import hotciv.variants.DeltaCivWorldLayout;

public class DeltaCivFactory implements CivFactory {

	public AgingStrategy createAging() {
		return new AlphaCivAging();
	}
	
	public UnitActionStrategy createUnitAction() {
		return new AlphaCivUnitAction();
	}

	public WorldLayoutStrategy createLayout(String[] layout) {
		return new DeltaCivWorldLayout(layout);
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
