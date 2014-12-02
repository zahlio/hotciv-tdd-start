package hotciv.teststubs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hotciv.common.CityImpl;
import hotciv.common.TileImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.common.City;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Tile;
import hotciv.framework.common.Unit;
import hotciv.framework.strategy.AttackStrategy;
import hotciv.framework.strategy.UnitStrategy;
import hotciv.throwable.NotAUnitException;
import hotciv.variants.attacks.EpsilonAttacking;
import hotciv.variants.die.OneSidedDie;
import hotciv.variants.units.ThetaCivUnits;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestThetaCivWithEpsilonStub {

	private AttackStrategy attackStrat;
	private Game game;
	
	@Before
	public void SetUp(){
		game = new ThetaEpsilonStub();
		attackStrat = new EpsilonAttacking(new OneSidedDie());
	}
	
	@Test
	public void ChariotShouldWin(){
		assertTrue("Chariot should beat Legion", attackStrat.performAttack(game, new Position(2,3), new Position(2,4)));
	}
	
	@Test
	public void ChariotShouldLose(){
		assertFalse("Chariot should lose when attacking Archer", attackStrat.performAttack(game, new Position(2,3), new Position(2,2)));
	}
}

class ThetaEpsilonStub implements Game{
	
	private UnitStrategy unitStrat;
	private HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();
	
	public ThetaEpsilonStub() {
		unitStrat = new ThetaCivUnits();
		units.put(new Position(2,3), (UnitImpl) unitStrat.produceUnit(ThetaCivUnits.CHARIOT, Player.RED));
		units.put(new Position(2,4), (UnitImpl) unitStrat.produceUnit(GameConstants.LEGION, Player.BLUE));
		units.put(new Position(2,2), (UnitImpl) unitStrat.produceUnit(GameConstants.ARCHER, Player.BLUE));
	}

	//ALL POSITION ARE PLAINS
	public Tile getTileAt(Position p) {
		return new TileImpl(GameConstants.PLAINS);
	}

	public Unit getUnitAt(Position p) {
		return units.get(p);
	}

	public City getCityAt(Position p) {
		return null;
	}

	public HashMap<Position, UnitImpl> getUnits() {
		return null;
	}

	public HashMap<Position, CityImpl> getCities() {
		return null;
	}

	public Player getPlayerInTurn() {
		return null;
	}

	public Player getWinner() {
		return null;
	}

	public int getAge() {
		return 0;
	}

	public boolean moveUnit(Position from, Position to) {
		return false;
	}

	public void endOfTurn() {
		
	}

	public void changeWorkForceFocusInCityAt(Position p, String balance) {
		
	}

	public void changeProductionInCityAt(Position p, String unitType)
			throws NotAUnitException {
		
	}

	public void performUnitActionAt(Position p) {
		
	}

	@Override
	public void setTranscription(boolean toggle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeTranscription() {
		// TODO Auto-generated method stub
		
	}
	
}