package hotciv.decorator;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.throwable.NotAUnitException;

import java.io.IOException;
import java.util.HashMap;

public class DecoratedGame implements Game {

	private DecoratorWriter writer;
	private GameImpl gameImpl;

	public DecoratedGame(GameImpl gameImpl) throws IOException {
		this.gameImpl = gameImpl;
		writer = new DecoratorWriter("Game Transcription.txt");
	}

	public Tile getTileAt(Position p) {
		return gameImpl.getTileAt(p);
	}

	public Unit getUnitAt(Position p) {
		return gameImpl.getUnitAt(p);
	}

	public City getCityAt(Position p) {
		return gameImpl.getCityAt(p);
	}

	public HashMap<Position, UnitImpl> getUnits() {
		return gameImpl.getUnits();
	}

	public HashMap<Position, CityImpl> getCities() {
		return gameImpl.getCities();
	}

	public Player getPlayerInTurn() {
		return gameImpl.getPlayerInTurn();
	}

	public Player getWinner() {
		return gameImpl.getWinner();
	}

	public int getAge() {
		return gameImpl.getAge();
	}

	public boolean moveUnit(Position from, Position to) {
		//TODO: NOT A GOOD WAY TO IMPLEMENT THIS
		Unit u = null;
		if(gameImpl.getUnitAt(from) !=null){
			u = gameImpl.getUnitAt(from);
		}
		if(writer.getToggle() && gameImpl.moveUnit(from, to)){
			writer.writeTranscript(gameImpl.getPlayerInTurn() + " moves " + u.getTypeString() + " from " + from.toString() + " to " + to.toString());
			return true;
		}
		return false;
	}

	public void endOfTurn() {
		if(writer.getToggle()){
				writer.writeTranscript(gameImpl.getPlayerInTurn() + " ends turn.\n");
		}
		gameImpl.endOfTurn();

	}

	public void changeWorkForceFocusInCityAt(Position p, String balance) {}

	public void changeProductionInCityAt(Position p, String unitType) throws NotAUnitException {
		if(writer.getToggle()){
			writer.writeTranscript(getCityAt(p).getOwner() + " city at " + p.toString() + " changed it's production to: " + unitType + "\n");
		}
		gameImpl.changeProductionInCityAt(p, unitType);
	}

	public void performUnitActionAt(Position p) {
		if(writer.getToggle() && getUnitAt(p)!=null){
			writer.writeTranscript(getUnitAt(p).getOwner() + " " + getUnitAt(p).getTypeString() + " has performed it's action.\n");
		}
		gameImpl.performUnitActionAt(p);
	}

	public void setTranscription() {
		writer.setTranscription();
	}

	public void closeTranscription() throws IOException {
		writer.closeTranscript();
	}

}
