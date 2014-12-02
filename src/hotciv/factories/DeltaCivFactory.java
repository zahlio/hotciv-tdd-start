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
import hotciv.variants.units.AlphaCivUnits;
import hotciv.variants.wincondition.AlphaCivWinCondition;
import hotciv.variants.worldlayout.DeltaCivWorldLayout;

public class DeltaCivFactory implements CivFactory {
	
	private String fileName;
	
	public DeltaCivFactory(String fileName) {
		this.fileName = fileName;
	}
	
	public AgingStrategy createAging() {
		return new AlphaCivAging();
	}
	
	public UnitStrategy createUnit() {
		return new AlphaCivUnits();
	}

	public WorldLayoutStrategy createLayout() {
		return new DeltaCivWorldLayout(Worlds.WORLD_DELTA);
	}

	public WinStrategy createWinner() {
		return new AlphaCivWinCondition();
	}

	public AttackStrategy createAttack() {
		return new AlphaCivAttacking();
	}
	
	public Command createCommand(){
		return new WriteCommand(fileName);
	}
}
