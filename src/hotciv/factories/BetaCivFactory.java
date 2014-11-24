package hotciv.factories;

import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitActionStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.AlphaCivAttacking;
import hotciv.variants.AlphaCivUnitAction;
import hotciv.variants.AlphaCivWorldLayout;
import hotciv.variants.BetaCivAging;
import hotciv.variants.BetaCivWinCondition;

public class BetaCivFactory implements CivFactory{

	public AgingStrategy createAging() {
		return new BetaCivAging();
	}
	
	public UnitActionStrategy createUnitAction() {
		return new AlphaCivUnitAction();
	}

	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout();
	}

	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}

	public WinStrategy createWinner() {
		return new BetaCivWinCondition();
	}
}
