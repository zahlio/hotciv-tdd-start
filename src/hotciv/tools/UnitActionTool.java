package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;

import java.awt.event.MouseEvent;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;

public class UnitActionTool extends AbstractTool {

	private Game game;
	Figure figure;
	
	public UnitActionTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game = game;
	}

	public void mouseDown(MouseEvent e, int x, int y) {
		Position p = GfxConstants.getPositionFromXY(x, y);
		if(game.getUnitAt(p)!=null && e.isShiftDown() && game.getUnitAt(p).getOwner().equals(game.getPlayerInTurn())){
			game.performUnitActionAt(p);
		}
	}
}
