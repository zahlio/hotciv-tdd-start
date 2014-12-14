package hotciv.view;

import hotciv.framework.*;

import java.awt.*;
import java.util.*;
import java.util.List;

import minidraw.framework.*;
import minidraw.standard.*;

/** CivDrawing is a specialized Drawing (model component) from
 * MiniDraw that dynamically builds the list of Figures for MiniDraw
 * to render the Unit and other information objects that are visible
 * in the Game instance.
 *
 * This is a TEMPLATE for the dSoftArk Exercise! This means
 * that it is INCOMPLETE and that there are several options
 * for CLEANING UP THE CODE when you add features to it!

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

public class CivDrawing 
  implements Drawing, GameObserver {
  
  private Drawing delegate;

  /** the Game instance that this UnitDrawing is going to render units
   * from */
  protected Game game;
  
  public CivDrawing( DrawingEditor editor, Game game ) {
    super();
    this.delegate = new StandardDrawing();
    this.game = game;

    // register this unit drawing as listener to any game state
    // changes...
    game.addObserver(this);
    // ... and build up the set of figures associated with
    // units in the game.
    defineUnitMap();
    defineCityMap();
    // and the set of 'icons' in the status panel
    defineIcons();
  }
  
  /** The CivDrawing should not allow client side
   * units to add and manipulate figures; only figures
   * that renders game objects are relevant, and these
   * should be handled by observer events from the game
   * instance. Thus this method is 'killed'.
   */
  public Figure add(Figure arg0) {
    return delegate.add(arg0);
  }

  /** store all moveable figures visible in this drawing = units */
  protected Map<Unit,UnitFigure> figureMap = null;
  protected Map<City, CityFigure> cityMap = null;
   
  /** erase the old list of units, and build a completely new
   * one from scratch by iterating over the game world and add
   * Figure instances for each unit in the world.
   */
  private void defineUnitMap() {
    // ensure no units of the old list are accidental in
    // the selection!
    clearSelection();

    figureMap = new HashMap<Unit,UnitFigure>();
    Position p;
    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        p = new Position(r,c);
        Unit unit = game.getUnitAt(p);
        if ( unit != null ) {
          String type = unit.getTypeString();
          // convert the unit's Position to (x,y) coordinates
          Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()),
                                   GfxConstants.getYFromRow(p.getRow()) );
          UnitFigure unitFigure =
            new UnitFigure( type, point, unit );
          unitFigure.addFigureChangeListener(this);
          figureMap.put(unit, unitFigure);

          // also insert in delegate list as it is
          // this list that is iterated by the
          // graphics rendering algorithms
          delegate.add(unitFigure);
        }
      }
    }
  }
  
  private void defineCityMap() {
	    // ensure no units of the old list are accidental in
	    // the selection!
	    clearSelection();

	   cityMap = new HashMap<City,CityFigure>();
	    Position p;
	    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
	      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
	        p = new Position(r,c);
	        City city = game.getCityAt(p);
	        if ( city != null ) {
	          // convert the unit's Position to (x,y) coordinates
	          Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()),
	                                   GfxConstants.getYFromRow(p.getRow()) );
	          CityFigure cityFigure =
	            new CityFigure(city, point);
	          cityFigure.addFigureChangeListener(this);
	          cityMap.put(city, cityFigure);

	          // also insert in delegate list as it is
	          // this list that is iterated by the
	          // graphics rendering algorithms
	          delegate.add(cityFigure);
	        }
	      }
	    }
	  }

  private ImageFigure turnShieldIcon;
  private void defineIcons() {
    // very much a template implementation :)
    turnShieldIcon = 
      new ImageFigure( "redshield",
                       new Point( GfxConstants.TURN_SHIELD_X,
                                  GfxConstants.TURN_SHIELD_Y ) ); 
    // insert in delegate figure list to ensure graphical
    // rendering.
    delegate.add(turnShieldIcon);
  }
 
  // === Observer Methods ===

  public void worldChangedAt(Position pos) {
    System.out.println( "CivDrawing: world changes at "+pos);
    clearSelection();
    // this is a really brute-force algorithm: destroy
    // all known units and build up the entire set again
    for ( Figure f : figureMap.values() ) {
      delegate.remove(f);
    }
    defineUnitMap();
    defineCityMap();
  }

  public void turnEnds(Player nextPlayer, int age) {
    System.out.println( "CivDrawing: turnEnds for "+
                        nextPlayer+" at "+age );
    String playername = "red";
    if ( nextPlayer == Player.BLUE ) { playername = "blue"; }
    turnShieldIcon.set( playername+"shield",
                        new Point( GfxConstants.TURN_SHIELD_X,
                                   GfxConstants.TURN_SHIELD_Y ) );
  }

  public void tileFocusChangedAt(Position position) {
    System.out.println( "Fake it: tileFocusChangedAt "+position );
  }

  @Override
  public void addToSelection(Figure arg0) {
    delegate.addToSelection(arg0);
  }

  @Override
  public void clearSelection() {
    delegate.clearSelection();
  }

  @Override
  public void removeFromSelection(Figure arg0) {
    delegate.removeFromSelection(arg0);
  }

  @Override
  public List<Figure> selection() {
    return delegate.selection();
  }

  @Override
  public void toggleSelection(Figure arg0) {
    delegate.toggleSelection(arg0);
  }

  @Override
  public void figureChanged(FigureChangeEvent arg0) {
    delegate.figureChanged(arg0);
  }

  @Override
  public void figureInvalidated(FigureChangeEvent arg0) {
    delegate.figureInvalidated(arg0);
  }

  @Override
  public void figureRemoved(FigureChangeEvent arg0) {
    delegate.figureRemoved(arg0);
  }

  @Override
  public void figureRequestRemove(FigureChangeEvent arg0) {
    delegate.figureRequestRemove(arg0);
  }

  @Override
  public void figureRequestUpdate(FigureChangeEvent arg0) {
    delegate.figureRequestUpdate(arg0);
  }

  @Override
  public void addDrawingChangeListener(DrawingChangeListener arg0) {
    delegate.addDrawingChangeListener(arg0);   
  }

  @Override
  public void removeDrawingChangeListener(DrawingChangeListener arg0) {
    delegate.removeDrawingChangeListener(arg0);
  }

  @Override
  public Figure findFigure(int arg0, int arg1) {
    
    return delegate.findFigure(arg0, arg1);
  }

  @Override
  public Iterator<Figure> iterator() {
    
    return delegate.iterator();
  }

  @Override
  public void lock() {
    delegate.lock();
  }

  @Override
  public Figure remove(Figure arg0) {
    
    return delegate.remove(arg0);
  }

  @Override
  public void requestUpdate() {
    delegate.requestUpdate();
    
  }

  @Override
  public void unlock() {
    delegate.unlock();
    
  }
}
