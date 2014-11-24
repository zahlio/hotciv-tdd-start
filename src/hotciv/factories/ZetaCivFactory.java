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
import hotciv.variants.SixSidedDie;
import hotciv.variants.ZetaCivAttacks;
import hotciv.variants.ZetaCivWinCondition;

public class ZetaCivFactory implements CivFactory {

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
		return new ZetaCivAttacks(new SixSidedDie());
	}

}
