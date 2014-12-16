package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;

import java.awt.event.MouseEvent;

import minidraw.framework.DrawingEditor;
import minidraw.standard.AbstractTool;

public class TileFocusTool extends AbstractTool {

	Game game;
	
	public TileFocusTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game = game;
	}
	
	@Override
	public void mouseDown(MouseEvent e, int x, int y) {
		Position p = GfxConstants.getPositionFromXY(x, y);
		game.setTileFocus(p);
	}
}
