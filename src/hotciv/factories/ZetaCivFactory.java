package hotciv.factories;

import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.DieStrategy;
import hotciv.framework.strategy.UnitActionStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.AlphaCivAging;
import hotciv.variants.AlphaCivUnitAction;
import hotciv.variants.AlphaCivWorldLayout;
import hotciv.variants.ZetaCivAttacks;
import hotciv.variants.ZetaCivWinCondition;

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
		return new ZetaCivWinCondition();
	}

	@Override
	public UnitActionStrategy createUnitAction() {
		return new AlphaCivUnitAction();
	}

	@Override
	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout();
	}

	@Override
	public AttackStrategy createAttack() {
		return new ZetaCivAttacks(die);
	}

}
