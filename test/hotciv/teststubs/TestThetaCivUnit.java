package hotciv.teststubs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hotciv.common.CityImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.common.City;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Tile;
import hotciv.framework.common.Unit;
import hotciv.framework.strategy.UnitStrategy;
import hotciv.throwable.NotAUnitException;
import hotciv.variants.units.ThetaCivUnits;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestThetaCivUnit {
	
	private Game game;
	private UnitStrategy unitStrategy;
	private Unit archer, legion, settler, chariot;
	
	@Before
	public void SetUp(){
		game = new ThetaCivGameStub();
		unitStrategy = new ThetaCivUnits();
		archer = unitStrategy.produceUnit(GameConstants.ARCHER, Player.RED);
		legion = unitStrategy.produceUnit(GameConstants.LEGION, Player.RED);
		settler = unitStrategy.produceUnit(GameConstants.SETTLER, Player.RED);
		chariot = unitStrategy.produceUnit(ThetaCivUnits.CHARIOT, Player.RED);
		game.getUnits().put(new Position(5,1), (UnitImpl) chariot);
		
	}
	
	@Test
	public void TheseShouldBeTheDefensiveStats(){
		assertEquals("Archer defensive stat should be 3", 3, archer.getDefensiveStrength());
		assertEquals("Legion defensive stat should be 2", 2, legion.getDefensiveStrength());
		assertEquals("Settler defensive stat should be 3", 3, settler.getDefensiveStrength());
		assertEquals("Chariot defensive stat should be 1", 1, chariot.getDefensiveStrength());
	}
	
	@Test
	public void TheseShouldBeTheOffensiveStats(){
		assertEquals("Archer offensive stat should be 2", 2, archer.getAttackingStrength());
		assertEquals("Legion offensive stat should be 4", 4, legion.getAttackingStrength());
		assertEquals("Settler offensive stat should be 0", 0, settler.getAttackingStrength());
		assertEquals("chariot offensive stat should be 3", 3, chariot.getAttackingStrength());
	}
	
	@Test
	public void chariotMoveShouldBe0(){
		unitStrategy.performUnitAction(game, new Position(5,1));
		assertEquals("chariot movecount should be 0", 0, chariot.getMoveCount());
	}
	
	@Test
	public void UnitHasNoSkills(){
		chariot.setSkillInUse();
		assertTrue("Archer skill should be in use", chariot.getIsSkillInUse());
	}
	
	@Test
	public void TheseUnitsShouldBeInTheGame(){
		assertTrue("archer should be in game", unitStrategy.hasUnit(archer.getTypeString()));
		assertTrue("legion should be in game", unitStrategy.hasUnit(legion.getTypeString()));
		assertTrue("settler should be in game", unitStrategy.hasUnit(settler.getTypeString()));
		
		assertTrue("chariot should not be in game", unitStrategy.hasUnit(chariot.getTypeString()));
	}
}

class ThetaCivGameStub implements Game {
	
	private HashMap<Position, UnitImpl> units = new HashMap<Position,UnitImpl>();

	public Tile getTileAt(Position p) {
		return null;
	}

	public Unit getUnitAt(Position p) {
		return units.get(p);
	}

	public City getCityAt(Position p) {	return null; }

	public HashMap<Position, UnitImpl> getUnits() {
		return units;
	}

	//unused
	public HashMap<Position, CityImpl> getCities() { return null; }
	public Player getPlayerInTurn() { return null; }
	public Player getWinner() {	return null; }
	public int getAge() { return 0; }
	public boolean moveUnit(Position from, Position to) { return false;	}
	public void endOfTurn() {}
	public void changeWorkForceFocusInCityAt(Position p, String balance) {}
	public void changeProductionInCityAt(Position p, String unitType) throws NotAUnitException {}
	public void performUnitActionAt(Position p) {}
}
