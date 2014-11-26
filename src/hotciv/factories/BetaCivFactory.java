package hotciv.factories;

import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.common.Worlds;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitActionStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.aging.BetaCivAging;
import hotciv.variants.attacks.AlphaCivAttacking;
import hotciv.variants.unitaction.AlphaCivUnitAction;
import hotciv.variants.wincondition.BetaCivWinCondition;
import hotciv.variants.worldlayout.AlphaCivWorldLayout;

public class BetaCivFactory implements CivFactory{

	public AgingStrategy createAging() {
		return new BetaCivAging();
	}
	
	public UnitActionStrategy createUnitAction() {
		return new AlphaCivUnitAction();
	}

	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout(Worlds.WORLD_ALPHA);
	}

	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}

	public WinStrategy createWinner() {
		return new BetaCivWinCondition();
	}
}
