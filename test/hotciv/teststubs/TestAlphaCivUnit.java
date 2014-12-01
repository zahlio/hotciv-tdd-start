package hotciv.teststubs;

import static org.junit.Assert.*;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Unit;
import hotciv.framework.strategy.UnitStrategy;
import hotciv.variants.units.AlphaCivUnits;
import hotciv.variants.units.ThetaCivUnits;

import org.junit.Before;
import org.junit.Test;

public class TestAlphaCivUnit {

	private UnitStrategy unitStrategy;
	private Unit archer, legion, settler;
	
	@Before
	public void SetUp(){
		unitStrategy = new AlphaCivUnits();
		archer = unitStrategy.produceUnit(GameConstants.ARCHER, Player.RED);
		legion = unitStrategy.produceUnit(GameConstants.LEGION, Player.RED);
		settler = unitStrategy.produceUnit(GameConstants.SETTLER, Player.RED);
	}
	
	@Test
	public void TheseShouldBeTheDefensiveStats(){
		assertEquals("Archer defensive stat should be 3", 3, archer.getDefensiveStrength());
		assertEquals("Legion defensive stat should be 2", 2, legion.getDefensiveStrength());
		assertEquals("Settler defensive stat should be 3", 3, settler.getDefensiveStrength());
	}
	
	@Test
	public void TheseShouldBeTheOffensiveStats(){
		assertEquals("Archer offensive stat should be 2", 2, archer.getAttackingStrength());
		assertEquals("Legion offensive stat should be 4", 4, legion.getAttackingStrength());
		assertEquals("Settler offensive stat should be 0", 0, settler.getAttackingStrength());
	}
	
	@Test
	public void MoveCountShouldBe1(){
		assertEquals("Archer movecount should be 1", 1, archer.getMoveCount());
	}
	
	@Test
	public void MoveCountShouldBe0(){
		archer.setHasMoved(true);
		assertEquals("Archer movecount should be 0", 0, archer.getMoveCount());
	}
	
	@Test
	public void UnitHasNoSkills(){
		archer.setSkillInUse();
		assertEquals("AlphaCivUnit skills should return false at all time", false, archer.getIsSkillInUse());
	}
	
	@Test
	public void TheseUnitsShouldBeInTheGame(){
		assertTrue("archer should be in game", unitStrategy.hasUnit(archer.getTypeString()));
		assertTrue("legion should be in game", unitStrategy.hasUnit(legion.getTypeString()));
		assertTrue("settler should be in game", unitStrategy.hasUnit(settler.getTypeString()));
		
		assertFalse("chariot should not be in game", unitStrategy.hasUnit(ThetaCivUnits.CHARIOT));
	}
	
}
