package hotciv.factories;

import hotciv.command.WriteCommand;
import hotciv.framework.abstractfactory.CivFactory;
import hotciv.framework.command.Command;
import hotciv.framework.common.Worlds;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.DieStrategy;
import hotciv.framework.strategy.UnitStrategy;
import hotciv.framework.strategy.WinStrategy;
import hotciv.framework.strategy.WorldLayoutStrategy;
import hotciv.variants.aging.BetaCivAging;
import hotciv.variants.attacks.EpsilonAttacking;
import hotciv.variants.units.SemiCivUnits;
import hotciv.variants.wincondition.EpsilonWinCondition;
import hotciv.variants.worldlayout.DeltaCivWorldLayout;

public class SemiCivFactory implements CivFactory {

	private String fileName;
	private DieStrategy die;
	
	public SemiCivFactory(String fileName, DieStrategy die) {
		this.fileName = fileName;
		this.die = die;
	}
	
	public AgingStrategy createAging() {
		return new BetaCivAging();
	}

	public WinStrategy createWinner() {
		return new EpsilonWinCondition();
	}

	public UnitStrategy createUnit() {
		return new SemiCivUnits();
	}

	public WorldLayoutStrategy createLayout() {
		return new DeltaCivWorldLayout(Worlds.WORLD_DELTA);
	}

	public AttackStrategy createAttack() {
		return new EpsilonAttacking(die);
	}
	
	public Command createCommand() {
		return new WriteCommand(fileName);
	}

}


