package hotciv.visual;

import hotciv.common.GameImpl;
import hotciv.factories.SemiCivFactory;
import hotciv.framework.Game;
import hotciv.tools.CompositionTool;
import hotciv.variants.die.SixSidedDie;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class ShowSemiCiv {

	public static void main(String[] args) {
		new ShowSemiCiv();
	}

	public ShowSemiCiv() {
		final Game game = new GameImpl(new SemiCivFactory(new SixSidedDie()));

		DrawingEditor editor = new MiniDrawApplication(
				"You are now playing SemiCiv", 
				new HotCivFactory4(game) );
		editor.open();

		editor.setTool(new CompositionTool(editor, game));
	}
}
