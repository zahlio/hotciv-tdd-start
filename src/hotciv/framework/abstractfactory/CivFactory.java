package hotciv.framework.abstractfactory;

import hotciv.framework.command.Command;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;

public interface CivFactory {
	
	public AgingStrategy createAging();

	public WinStrategy createWinner();
	
	public UnitStrategy createUnit();
	
	public WorldLayoutStrategy createLayout();
	
	public AttackStrategy createAttack();
	
	public Command createCommand();
}
