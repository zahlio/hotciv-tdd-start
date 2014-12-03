package hotciv.civs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hotciv.common.CityImpl;
import hotciv.common.DecoratedGame;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.common.UnitInfo;
import hotciv.factories.AlphaCivFactory;
import hotciv.framework.ThetaCivUnit;
import hotciv.framework.common.City;
import hotciv.framework.common.Game;
import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Unit;
import hotciv.throwable.NotAUnitException;

import org.junit.After;
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
	private DecoratedGame dec;
  
  private Position redCity; //blueCity, redArcher, redSettler, blueLegion;
  /** Fixture for alphaciv testing. */
  
  @Before
  public void setUp() {
    game = new GameImpl(new AlphaCivFactory());
    dec = new DecoratedGame((GameImpl) game);
    redCity = new Position(1,1);
    dec.setTranscription();
    //blueCity = new Position(4,1);
    //redArcher = new Position(2,0);
    //redSettler = new Position(4,3);
    //blueLegion = new Position(3,2);
  }
  
  @Test
  public void redShouldStart(){
	Player p = dec.getPlayerInTurn();
	assertEquals("Red player should start", Player.RED, p);
	assertNotNull("Red player should never be null at start", p);
  }
  
  @Test
  public void endTurnShouldChangePlayer(){
	  dec.endOfTurn();
	  Player p = dec.getPlayerInTurn();
	  assertEquals("Player should now be blue", Player.BLUE, p);
	  dec.endOfTurn();
	  Player p2 = dec.getPlayerInTurn();
	  assertEquals("Player should now be red again", Player.RED, p2);
	  
  }
  
  /*
   * Dynamic test for unit.
   * Returns the unit for further testing
   */
  public Unit shouldHaveUnitatXY(String constant, Position p){
	  Unit u = dec.getUnitAt(p);
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
  public void unitShouldBeOwnedByCurrentPlayer(){
	  assertFalse("Cannot move blue unit", dec.moveUnit(new Position(3,2), new Position(6, 7)));
  }
  
  @Test
  public void ShouldNotBeAbleToMoveOnOcean(){
	  assertFalse("You cannot move on oceans", dec.moveUnit(new Position(2,0), new Position(0, 1)));
  }
  
  @Test
  public void ShouldNotBeAbleToMoveOnMountains(){
	  assertFalse("you cannot move on mountains", dec.moveUnit(new Position(2,0), new Position(2, 2)));
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
	  dec.moveUnit(new Position(2,0), new Position(2,1));
	  UnitImpl u = (UnitImpl) dec.getUnitAt(new Position(2,1));
	  assertEquals("Unit moveCount should be 0", 0, u.getMoveCount());
	  assertFalse("This unit cannot move", dec.moveUnit(new Position(2,1), new Position(3,1)));
	  Utility.playRounds(dec,1);
	  assertEquals("Unit moveCount should be 1 again", 1, u.getMoveCount());
	  
  }
  
  @Test
  public void AttackerShouldWinAndMoveToLocation(){
	  Position red = new Position(2,0);
	  dec.getUnits().put(new Position(3,1), new UnitImpl(new UnitInfo(15,2,4),GameConstants.LEGION, Player.BLUE));
	  Position blue = new Position(3,1);
	  dec.moveUnit(red, blue);
	  Unit u = dec.getUnitAt(blue);
	  assertEquals("3,2 should be owned by red now", Player.RED, u.getOwner());
  }
  
  @Test
  public void RedLegionHasKilledBlueLegionAndConqueredBlueCity(){
	  Position redLegion = new Position(3,1);
	  Position blueLegion = new Position(4,1);
	  dec.getUnits().put(redLegion, new UnitImpl(new UnitInfo(15,2,4),GameConstants.LEGION, Player.RED));
	  dec.getUnits().put(blueLegion, new UnitImpl(new UnitInfo(15,2,4),GameConstants.LEGION, Player.BLUE));
	  
	  dec.moveUnit(redLegion, blueLegion);
	  Unit u = dec.getUnitAt(blueLegion);
	  City c = dec.getCityAt(blueLegion);
	  assertEquals("unit at (4,1) should be owned by red now", Player.RED, u.getOwner());
	  assertEquals("city at (4,1) should be owned by blue now", Player.RED, c.getOwner());
  }
  
  @Test
  public void TheRedProductionShouldBe6(){
	  CityImpl c = (CityImpl) dec.getCityAt(new Position(1,1));
	  Utility.playRounds(dec,1);
	  assertEquals("The production of the red city should be 6", 6, c.getResources());
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
    		  dec.getPlayerInTurn(),
    		  _u.getOwner()
	  );
  }
  
  @Test
  public void ShouldProduceArcher() throws NotAUnitException{
      dec.changeProductionInCityAt(redCity, GameConstants.ARCHER);
      Utility.playRounds(dec, 2);
      Unit u = dec.getUnitAt(redCity);
      shouldProduceX(u, GameConstants.ARCHER, redCity);
  }
  
  @Test
  public void ShouldProduceLegion() throws NotAUnitException{
      dec.changeProductionInCityAt(redCity, GameConstants.LEGION);
      Utility.playRounds(dec, 3);
      Unit u = dec.getUnitAt(redCity);
      shouldProduceX(u, GameConstants.LEGION, redCity);
  }
  
  @Test
  public void ShouldProduceSettler() throws NotAUnitException{
      dec.changeProductionInCityAt(redCity, GameConstants.SETTLER);
      Utility.playRounds(dec, 5);
      Unit u = dec.getUnitAt(redCity);
      shouldProduceX(u, GameConstants.SETTLER, redCity);
  }
  
  @Test(expected=NotAUnitException.class)
  public void ShouldNotProduceChariot() throws NotAUnitException{
      dec.changeProductionInCityAt(redCity, ThetaCivUnit.CHARIOT);
  }
  
  @Test
  public void AccumulatedEnoughToMake4ArchersAroundCity() throws NotAUnitException{
      Utility.playRounds(dec, 4);
      CityImpl c = (CityImpl) dec.getCityAt(redCity);
      assertEquals("The production should be 24", 24, c.getResources());
      for(int i=0;i<4;i++){
          dec.changeProductionInCityAt(redCity, GameConstants.ARCHER);
          Utility.playRounds(dec, 1);
      }
     
      Unit u = dec.getUnitAt(new Position(1,1));
      Unit u1 = dec.getUnitAt(new Position(0,1));
      Unit u2 = dec.getUnitAt(new Position(0,2));
      Unit u3 = dec.getUnitAt(new Position(1,2));
      
      //String _constant = GameConstants.ARCHER;
      assertEquals("There should be an Archer at (1,1)", GameConstants.ARCHER, u.getTypeString());
      assertEquals("There should be an Archer at (0,1)", GameConstants.ARCHER, u1.getTypeString());
      assertEquals("There should be an Archer at (0,2)", GameConstants.ARCHER, u2.getTypeString());
      assertEquals("There should be an Archer at (1,2)", GameConstants.ARCHER, u3.getTypeString());
  }
  
  @Test
  public void StartAgeShouldbe4000BC(){
	  assertEquals("Starting age should be -4000", -4000, dec.getAge());
  }
  
  @After
  public void closeTranscription(){
	  dec.closeTranscription();
  }
    
}