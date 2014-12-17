package hotciv.visual;

import hotciv.framework.Game;
import hotciv.stub.StubForMiniDraw;
import hotciv.view.GfxConstants;
import hotciv.view.TextFigure;

import java.awt.Point;
import java.awt.event.MouseEvent;

import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

/** Test the TextFigure to display age in
 * the status panel.
 * 
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
public class ShowText {

	public static void main(String[] args) {

		Game game = new StubForMiniDraw();

		DrawingEditor editor = 
				new MiniDrawApplication( "Click to see age text change...",  
						new HotCivFactory4(game) );
		editor.open();
		TextFigure tf = new TextFigure("4000 BC", 
				new Point(GfxConstants.AGE_TEXT_X,
						GfxConstants.AGE_TEXT_Y) );
		editor.drawing().add(tf);
		editor.setTool( new ChangeAgeTool(tf) );

	}
}

class ChangeAgeTool extends NullTool {
	private TextFigure textFigure;
	public ChangeAgeTool(TextFigure tf) {
		textFigure = tf;
	}
	int count = 0;
	public void mouseDown(MouseEvent e, int x, int y) {
		count++;
		if(count<40){
			textFigure.setText( ""+(4000-count*100)+" BC" );
		}else{
			textFigure.setText( ""+(count*100-4000)+" AD" );
		}
	}
}
