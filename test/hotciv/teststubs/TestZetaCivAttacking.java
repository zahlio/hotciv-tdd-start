package hotciv.teststubs;

import static org.junit.Assert.assertEquals;
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
import hotciv.variants.attacks.AlphaCivAttacking;
import hotciv.variants.attacks.EpsilonAttacking;
import hotciv.variants.attacks.ZetaCivAttacks;
import hotciv.variants.die.OneSidedDie;
import hotciv.variants.unitimpl.AlphaCivUnitImpl;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestZetaCivAttacking {

	private Game game;
	private Game game2;
	private AttackStrategy zetaAttack;

	@Before
	public void SetUp(){
		zetaAttack = new ZetaCivAttacks(new AlphaCivAttacking(), new EpsilonAttacking(new OneSidedDie()));
		game = new GameStubZetaCivAttacking(-3000);
		game2 = new GameStubZetaCivAttacking(-1000);
	}

	@Test
	public void AlphaAttackingShouldReturn1(){
		assertEquals("AlphaCiv attack should return 1", true, zetaAttack.performAttack(game, new Position(2,3), new Position(2,6)));
	}

	//THIS COULD BE TESTED ANOTHER WAY
	@Test public void AttackerShouldWin(){
		game2.getUnits().put(new Position(1,1),new UnitImpl(new AlphaCivUnitImpl(GameConstants.LEGION, Player.RED)));
		game2.getUnits().put(new Position(2,1), new UnitImpl(new AlphaCivUnitImpl(GameConstants.LEGION, Player.BLUE)));
		
		game2.getUnits().put(new Position(4,4), new UnitImpl(new AlphaCivUnitImpl(GameConstants.ARCHER, Player.RED)));
		game2.getUnits().put(new Position(5,4), new UnitImpl(new AlphaCivUnitImpl(GameConstants.ARCHER, Player.BLUE)));
		
		boolean redLegionShouldWin = zetaAttack.performAttack(game2, new Position(1,1), new Position(2,1));
		assertTrue("Attacker Won", redLegionShouldWin);
		boolean blueArcherShouldHaveDefended = zetaAttack.performAttack(game2, new Position(4,4), new Position(5,4));
		assertFalse("Defender Won", blueArcherShouldHaveDefended);
	}
}

class GameStubZetaCivAttacking implements Game{

	private int age;
	private HashMap<Position, UnitImpl> units = new HashMap<Position, UnitImpl>();

	public GameStubZetaCivAttacking(int age){
		this.age = age;
	}

	@Override
	public Tile getTileAt(Position p) {
		if(p.getRow()==1 && p.getColumn()==1){
			return new TileImpl(GameConstants.PLAINS);
		}else if(p.getRow()==2 && p.getColumn()==1){
			return new TileImpl(GameConstants.PLAINS);
		}else if(p.getRow()==4 && p.getColumn()==4){
			return new TileImpl(GameConstants.PLAINS);
		}else if(p.getRow()==5 && p.getColumn()==4){
			return new TileImpl(GameConstants.PLAINS);
		}
		return null;
	}

	@Override
	public Unit getUnitAt(Position p) {
		return units.get(p);
	}

	@Override
	public City getCityAt(Position p) {
		return null;
	}

	@Override
	public Player getPlayerInTurn() {
		return null;
	}

	@Override
	public Player getWinner() {
		return null;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public boolean moveUnit(Position from, Position to) {
		return false;
	}

	@Override
	public void endOfTurn() {

	}

	public HashMap<Position, CityImpl> getCities(){
		return null;
	}

	@Override
	public void changeWorkForceFocusInCityAt(Position p, String balance) {
	}

	@Override
	public void changeProductionInCityAt(Position p, String unitType) {
	}

	@Override
	public void performUnitActionAt(Position p) {
	}

	@Override
	public HashMap<Position, UnitImpl> getUnits() {
		return units;
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