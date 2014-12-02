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
import hotciv.variants.aging.AlphaCivAging;
import hotciv.variants.attacks.AlphaCivAttacking;
import hotciv.variants.units.ThetaCivUnits;
import hotciv.variants.wincondition.AlphaCivWinCondition;
import hotciv.variants.worldlayout.AlphaCivWorldLayout;

public class ThetaCivFactory implements CivFactory {

	private String fileName;
	
	public ThetaCivFactory(String fileName) {
		this.fileName = fileName;
	}
	
	public AgingStrategy createAging() {
		return new AlphaCivAging();
	}

	public WinStrategy createWinner() {
		return new AlphaCivWinCondition();
	}

	public UnitStrategy createUnit() {
		return new ThetaCivUnits();
	}

	public WorldLayoutStrategy createLayout() {
		return new AlphaCivWorldLayout(Worlds.WORLD_ALPHA);
	}

	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}

	public Command createCommand() {
		return new WriteCommand(fileName);
	}
}
