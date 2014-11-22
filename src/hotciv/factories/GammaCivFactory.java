package hotciv.factories;

import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackStrategy;
import hotciv.framework.CivFactory;
import hotciv.framework.UnitActionStrategy;
import hotciv.framework.WinStrategy;
import hotciv.framework.WorldLayoutStrategy;
import hotciv.variants.AlphaCivAging;
import hotciv.variants.AlphaCivAttacking;
import hotciv.variants.AlphaCivWinCondition;
import hotciv.variants.AlphaCivWorldLayout;
import hotciv.variants.GammaCivUnitAction;

public class GammaCivFactory implements CivFactory {

	public AgingStrategy createAging() {
		return new AlphaCivAging();
	}
	
	public UnitActionStrategy createUnitAction() {
		return new GammaCivUnitAction();
	}

	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout();
	}

	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}

	public WinStrategy createWinner() {
		return new AlphaCivWinCondition();
	}
}
