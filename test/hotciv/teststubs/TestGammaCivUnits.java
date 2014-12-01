package hotciv.teststubs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

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
import hotciv.variants.units.GammaCivUnits;
import hotciv.variants.units.ThetaCivUnits;

import org.junit.Before;
import org.junit.Test;

public class TestGammaCivUnits {
	
	private Game game;
	private UnitStrategy unitStrategy;
	private Unit archer, legion, settler;
	
	@Before
	public void SetUp(){
		game = new GammaCivGameStub();
		unitStrategy = new GammaCivUnits();
		archer = unitStrategy.produceUnit(GameConstants.ARCHER, Player.RED);
		legion = unitStrategy.produceUnit(GameConstants.LEGION, Player.RED);
		settler = unitStrategy.produceUnit(GameConstants.SETTLER, Player.RED);
		game.getUnits().put(new Position(5,1), (UnitImpl) archer);
		game.getUnits().put(new Position(6,1), (UnitImpl) settler);
		
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
	public void archerMoveShouldBe0(){
		unitStrategy.performUnitAction(game, new Position(5,1));
		assertEquals("Archer movecount should be 0", 0, archer.getMoveCount());
	}
	
	@Test
	public void settlerShouldHaveCreatedCity(){
		unitStrategy.performUnitAction(game, new Position(6,1));
		assertEquals("This city should be owned by red player", Player.RED, game.getCityAt(new Position(6,1)).getOwner());
	}
	
	@Test
	public void UnitHasNoSkills(){
		archer.setSkillInUse();
		assertTrue("Archer skill should be in use", archer.getIsSkillInUse());
	}
	
	@Test
	public void TheseUnitsShouldBeInTheGame(){
		assertTrue("archer should be in game", unitStrategy.hasUnit(archer.getTypeString()));
		assertTrue("legion should be in game", unitStrategy.hasUnit(legion.getTypeString()));
		assertTrue("settler should be in game", unitStrategy.hasUnit(settler.getTypeString()));
		
		assertFalse("chariot should not be in game", unitStrategy.hasUnit(ThetaCivUnits.CHARIOT));
	}
}

class GammaCivGameStub implements Game {
	
	private HashMap<Position, UnitImpl> units = new HashMap<Position,UnitImpl>();
	private HashMap<Position, CityImpl> cities = new HashMap<Position, CityImpl>();

	public Tile getTileAt(Position p) {
		return null;
	}

	public Unit getUnitAt(Position p) {
		return units.get(p);
	}

	public City getCityAt(Position p) {
		return cities.get(p);
	}

	public HashMap<Position, UnitImpl> getUnits() {
		return units;
	}

	public HashMap<Position, CityImpl> getCities() {
		return cities;
	}
	
	//unused
	public Player getPlayerInTurn() { return null; }
	public Player getWinner() {	return null; }
	public int getAge() { return 0; }
	public boolean moveUnit(Position from, Position to) { return false;	}
	public void endOfTurn() {}
	public void changeWorkForceFocusInCityAt(Position p, String balance) {}
	public void changeProductionInCityAt(Position p, String unitType) throws NotAUnitException {}
	public void performUnitActionAt(Position p) {}
}
