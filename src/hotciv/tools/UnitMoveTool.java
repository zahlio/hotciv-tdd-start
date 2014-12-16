package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import hotciv.view.UnitFigure;

import java.awt.event.MouseEvent;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;

public class UnitMoveTool extends AbstractTool {

	private int fromX, fromY;
	private int toX, toY;
	private Game game;
	private UnitFigure unitFigure;

	public UnitMoveTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game = game;
		unitFigure = null;
	}
	
	public void mouseDown(MouseEvent e, int x, int y){
		Position p = GfxConstants.getPositionFromXY(x, y);
		if(p.getColumn()<GameConstants.WORLDSIZE){
			fromX = x;
			fromY = y;
			Figure f = editor.drawing().findFigure(e.getX(), e.getY());
			if(f instanceof UnitFigure){
				editor.drawing().lock();
				unitFigure = (UnitFigure) f;
				toX = x;
				toY = y;
			}
		}
	}

	public void mouseDrag(MouseEvent e, int x, int y){
		if(unitFigure != null){
			unitFigure.moveBy(x - toX, y - toY);
			toX = x;
			toY = y;
		}else{
			
		}
	}

	/* TODO: figure out how to get the units to move the center of the tile */
	public void mouseUp(MouseEvent e, int x, int y){
		Position p = GfxConstants.getPositionFromXY(x, y);
		if(p.getColumn()<GameConstants.WORLDSIZE && unitFigure!=null){
			editor.drawing().unlock();
			Position from = GfxConstants.getPositionFromXY(fromX, fromY);
			Position to = GfxConstants.getPositionFromXY(x, y);

			if(!game.moveUnit(from, to)){
				int dx = fromX - x;
				int dy = fromY - y;
				unitFigure.moveBy(dx, dy);
			}else if(game.moveUnit(from, to)){
				int closestTileX = y % to.getRow();
				int closestTileY = x % to.getColumn();
				unitFigure.moveBy(-closestTileX, -closestTileY);
			}
			unitFigure = null;
		}
	}
}