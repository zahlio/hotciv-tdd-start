package hotciv.factories;

import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackStrategy;
import hotciv.framework.CivFactory;
import hotciv.framework.UnitActionStrategy;
import hotciv.framework.WinStrategy;
import hotciv.framework.WorldLayoutStrategy;
import hotciv.variants.AlphaCivAging;
import hotciv.variants.AlphaCivUnitAction;
import hotciv.variants.AlphaCivWorldLayout;
import hotciv.variants.EpsilonAttacking;
import hotciv.variants.EpsilonWinCondition;

public class EpsilonCivFactory implements CivFactory {

	public AgingStrategy createAging() {
		return new AlphaCivAging();
	}
	
	public UnitActionStrategy createUnitAction() {
		return new AlphaCivUnitAction();
	}

	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout();
	}

	public WinStrategy createWinner() {
		return new EpsilonWinCondition();
	}

	public AttackStrategy createAttack() {
		return new EpsilonAttacking();
	}
}
