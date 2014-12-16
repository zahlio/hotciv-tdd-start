package hotciv.stub;

import hotciv.common.CityImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

import java.util.HashMap;
import java.util.Map;

public class StubForMiniDraw implements Game {

	private HashMap<Position, UnitStub> units = new HashMap<Position, UnitStub>();
	private HashMap<Position, CityStub> cities = new HashMap<Position, CityStub>();

	private Position pos_archer_red;
	private Position pos_legion_blue;
	private Position pos_settler_red;

	private Unit red_archer, red_settler, blue_legion;
	private City red_city, blue_city;
	
	private int age = -4000;

	public StubForMiniDraw() { 

		pos_archer_red = new Position( 2, 0);
		pos_legion_blue = new Position( 3, 2);
		pos_settler_red = new Position( 4, 3);

		red_archer = new UnitStub( GameConstants.ARCHER, Player.RED );   
		red_settler = new UnitStub( GameConstants.SETTLER, Player.RED );   
		blue_legion = new UnitStub( GameConstants.LEGION, Player.BLUE );  
		
		red_city = new CityStub(Player.RED);
		blue_city = new CityStub(Player.BLUE);

		units.put(pos_archer_red, (UnitStub) red_archer);
		units.put(pos_settler_red, (UnitStub) red_settler);
		units.put(pos_legion_blue, (UnitStub) blue_legion);
		
		cities.put(new Position(1,1), (CityStub) red_city);
		cities.put(new Position(4,1), (CityStub) blue_city);
		defineWorld(1); 

		inTurn = Player.RED;
	}

	// === Unit handling ===


	public Unit getUnitAt(Position p) {
		return units.get(p);
	}

	public boolean moveUnit(Position from, Position to) {

		if (getUnitAt(from) == null || getUnitAt(from).getOwner() != getPlayerInTurn() ) { return false; }
		if (getTileAt(to).getTypeString().equals(GameConstants.OCEANS) || getTileAt(to).getTypeString().equals(GameConstants.MOUNTAINS) ) { return false; }
		if (getUnitAt(from).getMoveCount()==0 || !canMoveDistance(from, to)) { return false; }
		if (getUnitAt(to) != null && getUnitAt(to).getOwner().equals(getPlayerInTurn())) { return false; }

		
		units.put(to, (UnitStub) getUnitAt(from));
		units.remove(from);
		
		gameObserver.worldChangedAt(from);
		gameObserver.worldChangedAt(to);

		return true;
	}

	public boolean canMoveDistance(Position from, Position to){
		int moveHorizontal = from.getRow() - to.getRow();
		int moveVertical = from.getColumn() - to.getColumn();
		if(getUnitAt(from).getMoveCount() >= Math.abs(moveHorizontal)
				&& getUnitAt(from).getMoveCount() >= Math.abs(moveVertical)){
			return true;
		}else{
			return false;
		}
	}

	// === Turn handling ===
	private Player inTurn;
	//DOES NOT CHANGE THE ICON WTF?
	public void endOfTurn() {
		gameObserver.turnEnds(inTurn, age);
		if(inTurn == Player.RED){
			inTurn = Player.BLUE;
		}else{
			inTurn = Player.RED;
			age = age+100;
		}
	}
	
	public Player getPlayerInTurn() { return inTurn; }


	// === Observer handling ===
	protected GameObserver gameObserver;
	// observer list is only a single one...
	public void addObserver(GameObserver observer) {
		gameObserver = observer;
	} 

	// A simple implementation to draw the map of DeltaCiv
	protected Map<Position,Tile> world; 
	public Tile getTileAt( Position p ) { return world.get(p); }


	/** define the world.
	 * @param worldType 1 gives one layout while all other
	 * values provide a second world layout.
	 */
	protected void defineWorld(int worldType) {
		world = new HashMap<Position,Tile>();
		for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
			for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
				Position p = new Position(r,c);
				world.put( p, new StubTile(GameConstants.PLAINS));
			}
		}
	}

	public City getCityAt( Position p ) { return cities.get(p); }
	public Player getWinner() { return null; }
	public int getAge() { return age; }  
	public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
	public void changeProductionInCityAt( Position p, String unitType ) {
		gameObserver.worldChangedAt(p);
	}
	
	public void performUnitActionAt( Position p ) {
		System.out.println("perform action has been called");
		if(getUnitAt(p).getTypeString().equals(GameConstants.SETTLER)){
			City c = new CityStub(Player.RED);
			cities.put(p, (CityStub) c);
			units.remove(p);
			gameObserver.worldChangedAt(p);
		}
	}
	

	public void setTileFocus(Position position) {
		gameObserver.tileFocusChangedAt(position);
	}

	@Override
	public HashMap<Position, UnitImpl> getUnits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Position, CityImpl> getCities() {
		// TODO Auto-generated method stub
		return null;
	}

}

class UnitStub implements  Unit {
	private String type;
	private Player owner;
	public UnitStub(String type, Player owner) {
		this.type = type;
		this.owner = owner;
	}
	public String getTypeString() { return type; }
	public Player getOwner() { return owner; }
	public int getMoveCount() { return 1; }
	public int getDefensiveStrength() { return 0; }
	public int getAttackingStrength() { return 0; }
}

class CityStub implements City{

	private Player owner;
	
	public CityStub(Player owner) {
		this.owner = owner;
	}
	
	public Player getOwner() {
		return owner;
	}

	public int getSize() {
		return 1;
	}

	public String getProduction() {
		return "null";
	}

	public String getWorkforceFocus() {
		return null;
	}
}

