package hotciv.visual;

import hotciv.framework.Game;
import hotciv.stub.StubForMiniDraw;
import hotciv.tools.UnitMoveTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

/** Template code for exercise FRS 36.39.

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
public class ShowMove {

	public static void main(String[] args) {
		new ShowMove();
	}

	public ShowMove() {
		final Game game = new StubForMiniDraw();

		DrawingEditor editor = new MiniDrawApplication(
				"click and drag unit to move him around", 
				new HotCivFactory4(game) );
		editor.open();

		editor.setTool(new UnitMoveTool(editor, game));
	}
}
