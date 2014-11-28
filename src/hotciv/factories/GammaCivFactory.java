package hotciv.factories;

import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.common.Worlds;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.aging.AlphaCivAging;
import hotciv.variants.attacks.AlphaCivAttacking;
import hotciv.variants.units.GammaCivUnits;
import hotciv.variants.wincondition.AlphaCivWinCondition;
import hotciv.variants.worldlayout.AlphaCivWorldLayout;

public class GammaCivFactory implements CivFactory {

	public AgingStrategy createAging() {
		return new AlphaCivAging();
	}
	
	public UnitStrategy createUnit() {
		return new GammaCivUnits();
	}

	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout(Worlds.WORLD_ALPHA);
	}

	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}

	public WinStrategy createWinner() {
		return new AlphaCivWinCondition();
	}
}
