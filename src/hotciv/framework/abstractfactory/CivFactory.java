package hotciv.framework.abstractfactory;

import hotciv.common.UnitInfo;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;

import java.util.HashMap;

public interface CivFactory {
	
	public AgingStrategy createAging();

	public WinStrategy createWinner();
	
	public HashMap<String, UnitInfo> createUnit();
	
	public WorldLayoutStrategy createLayout();
	
	public AttackStrategy createAttack();
}
