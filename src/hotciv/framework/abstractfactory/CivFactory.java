package hotciv.framework.abstractfactory;

import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitActionStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;

public interface CivFactory {
	
	public AgingStrategy createAging();

	public WinStrategy createWinner();
	
	public UnitActionStrategy createUnitAction();
	
	public WorldLayoutStrategy createLayout(String[] layout);
	
	public AttackStrategy createAttack();
}
