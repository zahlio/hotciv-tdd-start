package hotciv.factories;

import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.common.Worlds;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.DieStrategy;
import hotciv.framework.strategy.UnitActionStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.aging.AlphaCivAging;
import hotciv.variants.attacks.AlphaCivAttacking;
import hotciv.variants.attacks.EpsilonAttacking;
import hotciv.variants.attacks.ZetaCivAttacks;
import hotciv.variants.unitaction.AlphaCivUnitAction;
import hotciv.variants.wincondition.BetaCivWinCondition;
import hotciv.variants.wincondition.EpsilonWinCondition;
import hotciv.variants.wincondition.ZetaCivWinCondition;
import hotciv.variants.worldlayout.AlphaCivWorldLayout;

public class ZetaCivFactory implements CivFactory {
	
	private DieStrategy die;
	
	//Needs to know if the AttackStrategy is played with 1 sided die for testing, or 6 sided for playing
	public ZetaCivFactory(DieStrategy die) {
		this.die = die;
	}

	public AgingStrategy createAging() {
		return new AlphaCivAging();
	}

	@Override
	public WinStrategy createWinner() {
		return new ZetaCivWinCondition(new BetaCivWinCondition(), new EpsilonWinCondition());
	}

	@Override
	public UnitActionStrategy createUnitAction() {
		return new AlphaCivUnitAction();
	}

	@Override
	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout(Worlds.WORLD_ALPHA);
	}

	@Override
	public AttackStrategy createAttack() {
		return new ZetaCivAttacks(new AlphaCivAttacking(), new EpsilonAttacking(die));
	}

}
