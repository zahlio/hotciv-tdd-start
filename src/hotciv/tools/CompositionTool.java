package hotciv.tools;

import hotciv.framework.Game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;

public class CompositionTool implements Tool{

	private Tool unitMoveTool;
	private Tool tileFocusTool;
	private Tool endOfTurnTool;
	private Tool unitActionTool;
	
	public CompositionTool(DrawingEditor editor, Game game) {
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

	@Override
	public void keyDown(KeyEvent arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMove(MouseEvent arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
