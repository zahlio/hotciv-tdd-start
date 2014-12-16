package hotciv.visual;

import hotciv.framework.Game;
import hotciv.stub.StubForMiniDraw;
import hotciv.tools.CompositionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class ShowMiniDrawStub {

	public static void main(String[] args) {
		Game game = new StubForMiniDraw();

		DrawingEditor editor = 
				new MiniDrawApplication( "Click any tile to set focus",  
						new HotCivFactory4(game) );
		editor.open();
		editor.showStatus("Play the game");

		// Replace the setting of the tool with your SetFocusTool implementation.
		editor.setTool(new CompositionTool(editor, game) );
	}
}