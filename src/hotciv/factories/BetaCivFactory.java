package hotciv.factories;

import hotciv.common.UnitInfo;
import hotciv.framework.GameConstants;
import hotciv.framework.Worlds;
import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitActionStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.aging.BetaCivAging;
import hotciv.variants.attacks.AlphaCivAttacking;
import hotciv.variants.unitaction.AlphaCivUnitAction;
import hotciv.variants.wincondition.BetaCivWinCondition;
import hotciv.variants.worldlayout.AlphaCivWorldLayout;

import java.util.HashMap;

public class BetaCivFactory implements CivFactory{
	
	public AgingStrategy createAging() {
		return new BetaCivAging();
	}
	
	public HashMap<String, UnitInfo> createUnit() {
		HashMap<String, UnitInfo> unitInfo = new HashMap<String, UnitInfo>();
		unitInfo.put(GameConstants.ARCHER, new UnitInfo(10,3,2));
		unitInfo.put(GameConstants.SETTLER, new UnitInfo(30,3,0));
		unitInfo.put(GameConstants.LEGION, new UnitInfo(15,2,4));
		return unitInfo;
	}

	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout(Worlds.WORLD_ALPHA);
	}

	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}

	public WinStrategy createWinner() {
		return new BetaCivWinCondition();
	}
	
	public UnitActionStrategy createUnitAction() {
		return new AlphaCivUnitAction();
	}
}
