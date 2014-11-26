package hotciv.factories;

import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.common.Worlds;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitActionStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.aging.AlphaCivAging;
import hotciv.variants.attacks.AlphaCivAttacking;
import hotciv.variants.unitaction.ThetaCivUnitAction;
import hotciv.variants.wincondition.AlphaCivWinCondition;
import hotciv.variants.worldlayout.AlphaCivWorldLayout;

public class ThetaCivFactory implements CivFactory {

	public AgingStrategy createAging() {
		return new AlphaCivAging();
	}

	public WinStrategy createWinner() {
		return new AlphaCivWinCondition();
	}

	public UnitActionStrategy createUnitAction() {
		return new ThetaCivUnitAction();
	}

	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout(Worlds.WORLD_ALPHA);
	}

	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}

}
