package hotciv.factories;

import hotciv.common.UnitInfo;
import hotciv.framework.GameConstants;
import hotciv.framework.Worlds;
import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.DieStrategy;
import hotciv.framework.strategy.UnitActionStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.aging.AlphaCivAging;
import hotciv.variants.attacks.EpsilonAttacking;
import hotciv.variants.unitaction.AlphaCivUnitAction;
import hotciv.variants.wincondition.BetaCivWinCondition;
import hotciv.variants.wincondition.EpsilonWinCondition;
import hotciv.variants.wincondition.ZetaCivWinCondition;
import hotciv.variants.worldlayout.AlphaCivWorldLayout;

import java.util.HashMap;

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

	public HashMap<String, UnitInfo> createUnit() {
		HashMap<String, UnitInfo> unitInfo = new HashMap<String, UnitInfo>();
		unitInfo.put(GameConstants.ARCHER, new UnitInfo(10,3,2));
		unitInfo.put(GameConstants.SETTLER, new UnitInfo(30,3,0));
		unitInfo.put(GameConstants.LEGION, new UnitInfo(15,2,4));
		return unitInfo;
	}

	@Override
	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout(Worlds.WORLD_ALPHA);
	}

	@Override
	public AttackStrategy createAttack() {
		return new EpsilonAttacking(die);
	}
	
	public UnitActionStrategy createUnitAction() {
		return new AlphaCivUnitAction();
	}
}
