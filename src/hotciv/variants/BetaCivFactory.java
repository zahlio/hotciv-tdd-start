package hotciv.variants;

import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackStrategy;
import hotciv.framework.CivFactory;
import hotciv.framework.UnitActionStrategy;
import hotciv.framework.WinStrategy;
import hotciv.framework.WorldLayoutStrategy;

public class BetaCivFactory implements CivFactory{

	public AgingStrategy createAging() {
		return new BetaCivAging();
	}
	
	public WinStrategy createWinner() {
		return new BetaCivWinCondition();
	}
	
	public UnitActionStrategy createUnitAction() {
		return new AlphaCivUnitAction();
	}

	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout();
	}

	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}
}
