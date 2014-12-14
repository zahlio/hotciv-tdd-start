package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import hotciv.view.UnitFigure;

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
		Figure f = editor.drawing().findFigure(x, y);
		if(game.getUnitAt(p)!=null && e.isShiftDown()){
			game.performUnitActionAt(p);
		}
		if(f instanceof UnitFigure && game.getCityAt(p)!=null){
			//CityFigure c = new CityFigure(game.getCityAt(p), 
			//		new Point(GfxConstants.getXFromColumn(p.getColumn()),
			//				GfxConstants.getYFromRow(p.getRow())));
			//editor.drawing().add(c);
		}
	}
}
