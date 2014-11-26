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
import hotciv.variants.attacks.EpsilonAttacking;
import hotciv.variants.unitaction.AlphaCivUnitAction;
import hotciv.variants.wincondition.EpsilonWinCondition;
import hotciv.variants.worldlayout.AlphaCivWorldLayout;

public class EpsilonCivFactory implements CivFactory {
	
	private DieStrategy die;
	
	//Needs to know if the AttackStrategy is played with 1 sided die for testing, or 6 sided for playing
	public EpsilonCivFactory(DieStrategy die) {
		this.die = die;
	}

	public AgingStrategy createAging() {
		return new AlphaCivAging();
	}
	
	public UnitActionStrategy createUnitAction() {
		return new AlphaCivUnitAction();
	}

	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout(Worlds.WORLD_ALPHA);
	}

	public WinStrategy createWinner() {
		return new EpsilonWinCondition();
	}

	public AttackStrategy createAttack() {
		return new EpsilonAttacking(die);
	}
}
