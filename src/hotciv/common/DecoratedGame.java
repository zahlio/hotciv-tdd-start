package hotciv.common;

import hotciv.command.WriteCommand;
import hotciv.framework.common.City;
import hotciv.framework.common.Game;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.common.Tile;
import hotciv.framework.common.Unit;
import hotciv.throwable.NotAUnitException;

import java.util.HashMap;

public class DecoratedGame implements Game {

	private WriteCommand command;
	private GameImpl gameImpl;

	public DecoratedGame(GameImpl gameImpl) {
		this.gameImpl = gameImpl;
		command = new WriteCommand("Game Transcription.txt");
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
		if(command.getToggle()){
			command.writeTranscript(gameImpl.getPlayerInTurn() + " moves " + gameImpl.getUnitAt(from).getTypeString() + " from " + from.toString() + " to " + to.toString());		
		}
		return gameImpl.moveUnit(from, to);
	}

	public void endOfTurn() {
		if(command.getToggle()){
				command.writeTranscript(gameImpl.getPlayerInTurn() + " ends turn.\n");
		}
		gameImpl.endOfTurn();

	}

	public void changeWorkForceFocusInCityAt(Position p, String balance) {}

	public void changeProductionInCityAt(Position p, String unitType) throws NotAUnitException {
		if(command.getToggle()){
			command.writeTranscript(getCityAt(p).getOwner() + " city at " + p.toString() + " changed it's production to: " + unitType + "\n");
		}
		gameImpl.changeProductionInCityAt(p, unitType);
	}

	public void performUnitActionAt(Position p) {
		if(command.getToggle() && getUnitAt(p)!=null){
			command.writeTranscript(getUnitAt(p).getOwner() + " " + getUnitAt(p).getTypeString() + " has performed it's action.\n");
		}
		gameImpl.performUnitActionAt(p);
	}

	public void setTranscription() {
		command.setTranscription();
	}

	public void closeTranscription() {
		command.closeTranscript();
	}

}
