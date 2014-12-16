package hotciv.tools;

import hotciv.framework.Game;

import java.awt.event.MouseEvent;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.AbstractTool;

public class CompositionTool extends AbstractTool{

	private Tool unitMoveTool;
	private Tool tileFocusTool;
	private Tool endOfTurnTool;
	private Tool unitActionTool;
	
	public CompositionTool(DrawingEditor editor, Game game) {
		super(editor);
		unitMoveTool = new UnitMoveTool(editor, game);
		tileFocusTool = new TileFocusTool(editor, game);
		endOfTurnTool = new EndOfTurnTool(editor, game);
		unitActionTool = new UnitActionTool(editor, game);
	}
	
	public void mouseDown(MouseEvent e, int x, int y) {
		unitMoveTool.mouseDown(e, x, y);
		tileFocusTool.mouseDown(e, x, y);
		endOfTurnTool.mouseDown(e, x, y);
		unitActionTool.mouseDown(e, x, y);
	}
	
	public void mouseDrag(MouseEvent e, int x, int y) {
		unitMoveTool.mouseDrag(e, x, y);
	}
	
	public void mouseUp(MouseEvent e, int x, int y) {
		unitMoveTool.mouseUp(e, x, y);
	}

}
