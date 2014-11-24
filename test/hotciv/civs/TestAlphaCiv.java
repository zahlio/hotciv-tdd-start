package hotciv.civs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.factories.AlphaCivFactory;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

import org.junit.Before;
import org.junit.Test;

/** Skeleton class for AlphaCiv test cases 

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class TestAlphaCiv {
  private Game game;
  private Position redCity; //blueCity, redArcher, redSettler, blueLegion;
  /** Fixture for alphaciv testing. */
  
  @Before
  public void setUp() {
    game = new GameImpl(new AlphaCivFactory());
    redCity = new Position(1,1);
    //blueCity = new Position(4,1);
    //redArcher = new Position(2,0);
    //redSettler = new Position(4,3);
    //blueLegion = new Position(3,2);
  }
  
  @Test
  public void redShouldStart(){
	Player p = game.getPlayerInTurn();
	assertEquals("Red player should start", Player.RED, p);
	assertNotNull("Red player should never be null at start", p);
  }
  
  @Test
  public void endTurnShouldChangePlayer(){
	  game.endOfTurn();
	  Player p = game.getPlayerInTurn();
	  assertEquals("Player should now be blue", Player.BLUE, p);
	  game.endOfTurn();
	  Player p2 = game.getPlayerInTurn();
	  assertEquals("Player should now be red again", Player.RED, p2);
	  
  }
  
  /*
   * Dynamic test for tile.
   */
  public void shouldHaveTileatXY(String constant, Position p){
	  Tile t = game.getTileAt(p);
	  assertEquals(
		  String.format("Should have %s at (%d, %d)", constant, p.getRow(), p.getColumn()), 
		  constant, 
		  t.getTypeString()
	  );
  }
  
  @Test
  public void shouldHaveOceans(){
	  shouldHaveTileatXY(GameConstants.OCEANS, new Position(0,1));
  }
  
  @Test
  public void shouldHaveHills(){
	  shouldHaveTileatXY(GameConstants.HILLS, new Position(1,0));
  }
  
  @Test
  public void shouldHaveMountains(){
	  shouldHaveTileatXY(GameConstants.MOUNTAINS, new Position(2,2));
  }

  /*
   * Dynamic test for unit.
   * Returns the unit for further testing
   */
  public Unit shouldHaveUnitatXY(String constant, Position p){
	  Unit u = game.getUnitAt(p);
	  assertEquals(
		  String.format("There should be an %s at (%d, %d)", constant, p.getRow(), p.getColumn()), 
		  constant, 
		  u.getTypeString()
	  );
	  return u;
  }
  
  @Test
  public void shouldHaveRedArcher(){
	  Position p = new Position(2,0);
	  assertEquals(
			  "Archer should be owned by Red",
			  Player.RED,
			  shouldHaveUnitatXY(GameConstants.ARCHER, p).getOwner()); // To fluer med et sm�k
  }
  
  @Test
  public void shouldHaveBlueLegion(){
	  Position p = new Position(3,2);
	  assertEquals(
			  "Archer should be owned by Blue",
			  Player.BLUE,
			  shouldHaveUnitatXY(GameConstants.LEGION, p).getOwner()); // To fluer med et sm�k
  }
  
  @Test
  public void shouldHaveRedSettler(){
	  Position p = new Position(4,3);
	  assertEquals(
			  "Archer should be owned by Red",
			  Player.RED,
			  shouldHaveUnitatXY(GameConstants.SETTLER, p).getOwner()); // To fluer med et sm�k
  }
  
  @Test
  public void noUnitIshere(){
	  assertFalse("There is no unit to move here", game.moveUnit(new Position(7,6), new Position(6, 7)));
  }
  
  @Test
  public void unitShouldBeOwnedByCurrentPlayer(){
	  assertFalse("Cannot move blue unit", game.moveUnit(new Position(3,2), new Position(6, 7)));
  }
  
  @Test
  public void ShouldNotBeAbleToMoveOnOcean(){
	  assertFalse("You cannot move on oceans", game.moveUnit(new Position(2,0), new Position(0, 1)));
  }
  
  @Test
  public void ShouldNotBeAbleToMoveOnMountains(){
	  assertFalse("you cannot move on mountains", game.moveUnit(new Position(2,0), new Position(2, 2)));
  }
    
  @Test
  public void ToShouldbeInsideWorld(){
	  assertFalse("To should be inside world", game.moveUnit(new Position(2,0), new Position(17, 5)));
  }
  
  @Test
  public void ShouldBeValidMove(){
	  assertTrue("This should be a valid move", ((GameImpl)game).canMoveDistance(new Position(2,0), new Position(3,1)));
  }
  
  @Test
  public void MoveShouldBeTooFar(){
	  assertFalse("Should not be able to move this far", ((GameImpl)game).canMoveDistance(new Position(2,0), new Position(2,2)));
  }
  
  @Test
  public void UnitHasMovedAndReset(){
	  game.moveUnit(new Position(2,0), new Position(2,1));
	  Unit u = game.getUnitAt(new Position(2,1));
	  assertEquals("Unit moveCount should be 0", 0, u.getMoveCount());
	  assertFalse("This unit cannot move", game.moveUnit(new Position(2,1), new Position(3,1)));
	  Utility.playRounds(game,1);
	  assertEquals("Unit moveCount should be 1 again", 1, u.getMoveCount());
	  
  }
  
  @Test
  public void AttackerShouldWinAndMoveToLocation(){
	  Position red = new Position(2,0);
	  game.getUnits().put(new Position(3,1), new UnitImpl(GameConstants.LEGION, Player.BLUE));
	  Position blue = new Position(3,1);
	  game.moveUnit(red, blue);
	  Unit u = game.getUnitAt(blue);
	  assertEquals("3,2 should be owned by red now", Player.RED, u.getOwner());
  }
  
  @Test
  public void TheRedProductionShouldBe6(){
	  CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
	  Utility.playRounds(game,1);
	  assertEquals("The production of the red city should be 6", 6, c.getResources());
  }
  
  /*
   * Dynamic test for city at pos.
   */
  public void thereShouldBeCityAtXY(Player player, Position p){
	  City c = game.getCityAt(p);
	  assertEquals(
			  String.format(
					  "There should be a %s city at (%d,%d)", 
					  c, 
					  p.getRow(), 
					  p.getColumn()
			  ), 
			  player, 
			  c.getOwner()
	  );
  }
  
  @Test
  public void ThereShouldBeARedCityAt(){
	  thereShouldBeCityAtXY(Player.RED, new Position(1,1));
  }
  
  @Test
  public void ThereShouldABlueCityAt(){
	  thereShouldBeCityAtXY(Player.BLUE, new Position(4,1));
  }
  
  /*
   * Dynamic test for production.
   */
  public void shouldProduceX(Unit _u, String _unitConstant, Position p){
	  assertEquals(
			  String.format(
					  "A new %s should have been created at (%d, %d)",
					  _unitConstant, 
					  p.getRow(),
					  p.getColumn()
			  ), 
			  _unitConstant,
			  _u.getTypeString()
	  );
	  
      assertEquals(
    		  String.format(
    				  "The %s should be owned by the current player",
    				  _unitConstant
			  ), 
    		  game.getPlayerInTurn(),
    		  _u.getOwner()
	  );
  }
  
  @Test
  public void ShouldProduceArcher(){
      game.changeProductionInCityAt(redCity, GameConstants.ARCHER);
      Utility.playRounds(game, 2);
      Unit u = game.getUnitAt(redCity);
      shouldProduceX(u, GameConstants.ARCHER, redCity);
  }
  
  @Test
  public void ShouldProduceLegion(){
      game.changeProductionInCityAt(redCity, GameConstants.LEGION);
      Utility.playRounds(game, 3);
      Unit u = game.getUnitAt(redCity);
      shouldProduceX(u, GameConstants.LEGION, redCity);
  }
  
  @Test
  public void ShouldProduceSettler(){
      game.changeProductionInCityAt(redCity, GameConstants.SETTLER);
      Utility.playRounds(game, 5);
      Unit u = game.getUnitAt(redCity);
      shouldProduceX(u, GameConstants.SETTLER, redCity);
  }
  
  @Test
  public void AccumulatedEnoughToMake4ArchersAroundCity(){
      Utility.playRounds(game, 4);
      CityImpl c = (CityImpl) game.getCityAt(redCity);
      assertEquals("The production should be 24", 24, c.getResources());
      for(int i=0;i<4;i++){
          game.changeProductionInCityAt(redCity, GameConstants.ARCHER);
          Utility.playRounds(game, 1);
      }
     
      Unit u = game.getUnitAt(new Position(1,1));
      Unit u1 = game.getUnitAt(new Position(0,1));
      Unit u2 = game.getUnitAt(new Position(0,2));
      Unit u3 = game.getUnitAt(new Position(1,2));
      
      //String _constant = GameConstants.ARCHER;
      assertEquals("There should be an Archer at (1,1)", GameConstants.ARCHER, u.getTypeString());
      assertEquals("There should be an Archer at (0,1)", GameConstants.ARCHER, u1.getTypeString());
      assertEquals("There should be an Archer at (0,2)", GameConstants.ARCHER, u2.getTypeString());
      assertEquals("There should be an Archer at (1,2)", GameConstants.ARCHER, u3.getTypeString());
  }
  
  @Test
  public void StartAgeShouldbe4000BC(){
	  assertEquals("Starting age should be -4000", -4000, game.getAge());
  }
  
  @Test
  public void RedShouldTheBeWinnerByYear3000BC(){
	  Utility.playRounds(game,10);
	  assertEquals("Red should have won by now", Player.RED, game.getWinner());
  }
    
}