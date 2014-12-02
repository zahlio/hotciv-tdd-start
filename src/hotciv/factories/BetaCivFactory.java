package hotciv.factories;

import hotciv.command.WriteCommand;
import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.command.Command;
import hotciv.framework.common.Worlds;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.aging.BetaCivAging;
import hotciv.variants.attacks.AlphaCivAttacking;
import hotciv.variants.units.AlphaCivUnits;
import hotciv.variants.wincondition.BetaCivWinCondition;
import hotciv.variants.worldlayout.AlphaCivWorldLayout;

public class BetaCivFactory implements CivFactory{
	
	private String fileName;
	
	public BetaCivFactory(String fileName) {
		this.fileName = fileName;
	}

	public AgingStrategy createAging() {
		return new BetaCivAging();
	}
	
	public UnitStrategy createUnit() {
		return new AlphaCivUnits();
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

	public Command createCommand() {
		return new WriteCommand(fileName);
	}
}
