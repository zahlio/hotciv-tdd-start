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
import hotciv.variants.AlphaCivWorldLayout;

public class AlphaCivFactory implements CivFactory {

	public AgingStrategy createAging() {
		return new AlphaCivAging();
	}
	
	public WinStrategy createWinner() {
		return new AlphaCivWinCondition();
	}
	
	public UnitActionStrategy createUnitAction() {
		return new AlphaCivUnitAction();
	}

	public WorldLayoutStrategy createLayout(String[] layout) {
		return new AlphaCivWorldLayout(layout);
	}

	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}
}
