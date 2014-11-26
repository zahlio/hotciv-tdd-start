package hotciv.factories;

import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.common.Worlds;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitActionStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.aging.BetaCivAging;
import hotciv.variants.attacks.EpsilonAttacking;
import hotciv.variants.die.SixSidedDie;
import hotciv.variants.unitaction.GammaCivUnitAction;
import hotciv.variants.wincondition.EpsilonWinCondition;
import hotciv.variants.worldlayout.DeltaCivWorldLayout;

public class SemiCiv implements CivFactory {

	public AgingStrategy createAging() {
		return new BetaCivAging();
	}

	public WinStrategy createWinner() {
		return new EpsilonWinCondition();
	}

	public UnitActionStrategy createUnitAction() {
		return new GammaCivUnitAction();
	}

	public WorldLayoutStrategy createLayout() {
		return new DeltaCivWorldLayout(Worlds.WORLD_DELTA);
	}

	public AttackStrategy createAttack() {
		return new EpsilonAttacking(new SixSidedDie());
	}

}
