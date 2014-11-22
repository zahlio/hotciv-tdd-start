package hotciv.factories;

import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackStrategy;
import hotciv.framework.CivFactory;
import hotciv.framework.UnitActionStrategy;
import hotciv.framework.WinStrategy;
import hotciv.framework.WorldLayoutStrategy;
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
