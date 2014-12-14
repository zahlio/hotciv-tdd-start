package hotciv.visual;

import hotciv.framework.Game;
import hotciv.stub.StubForMiniDraw;
import hotciv.tools.UnitActionTool;
import hotciv.view.MapView;

import javax.swing.JTextField;

import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.StandardDrawing;

/** Template code for exercise FRS 36.43.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University

   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowAction {

	public static void main(String[] args) {
		Game game = new StubForMiniDraw();

		DrawingEditor editor = 
				new MiniDrawApplication( "Shift-Click unit to invoke its action",  
						new HotCivFactory4(game) );
		editor.open();

		
		editor.showStatus("Shift-Click on unit to see Game's performAction method being called.");
		// Replace the setting of the tool with your ActionTool implementation.
		editor.setTool( new UnitActionTool(editor, game) );
	}
}

class HotCivFactory5 implements Factory {
	  private Game game;
	  public HotCivFactory5(Game g) { game = g; }

	  public DrawingView createDrawingView( DrawingEditor editor ) {
	    DrawingView view = 
	      new MapView(editor, game);
	    return view;
	  }

	  public Drawing createDrawing( DrawingEditor editor ) {
	    return new StandardDrawing();
	  }

	  public JTextField createStatusField( DrawingEditor editor ) {
	    return null;
	  }
	}
