package hotciv.tools;

import java.awt.event.MouseEvent;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.AbstractTool;

public class EndOfTurnTool extends AbstractTool {

	private Game game;

	public EndOfTurnTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game = game;
	}

	@Override
	public void mouseDown(MouseEvent e, int x, int y) {
		if(GfxConstants.TURN_SHIELD_X < e.getX() && GfxConstants.TURN_SHIELD_X+GfxConstants.TILESIZE > e.getX()
				&& GfxConstants.TURN_SHIELD_Y < e.getY() && GfxConstants.TURN_SHIELD_Y+GfxConstants.TILESIZE*1.5 > e.getY()){
			game.endOfTurn();
		}
	}

}
