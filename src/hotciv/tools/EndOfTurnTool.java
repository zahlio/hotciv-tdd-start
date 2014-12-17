package hotciv.tools;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import hotciv.view.TextFigure;

import java.awt.Point;
import java.awt.event.MouseEvent;

import minidraw.framework.DrawingEditor;
import minidraw.standard.AbstractTool;

public class EndOfTurnTool extends AbstractTool {

	private Game game;
	private TextFigure textFigure;

	public EndOfTurnTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game = game;
		textFigure = new TextFigure(String.valueOf(Math.abs(game.getAge())
				+ " BC"), 
					new Point(GfxConstants.AGE_TEXT_X,
							GfxConstants.AGE_TEXT_Y) );
		editor.drawing().add(textFigure);
	}

	public void mouseDown(MouseEvent e, int x, int y) {
		if(GfxConstants.TURN_SHIELD_X < x && 
				GfxConstants.TURN_SHIELD_X+GfxConstants.TILESIZE > x && 
				GfxConstants.TURN_SHIELD_Y < y && 
				GfxConstants.TURN_SHIELD_Y+GfxConstants.TILESIZE*1.5 > y){
			game.endOfTurn();
		}
		if(game.getAge()<0){
			textFigure.setText( ""+(Math.abs(game.getAge()))+" BC" );
		}else{
			textFigure.setText( ""+(game.getAge())+" AD" );
		}
	}
	
	public void mouseUp(MouseEvent e, int x, int y) {
		editor.drawing().unlock();
	}
}

