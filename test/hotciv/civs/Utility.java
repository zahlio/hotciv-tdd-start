package hotciv.civs;

import hotciv.common.CityImpl;
import hotciv.framework.common.Game;
import hotciv.framework.common.Position;

public class Utility {

	public static void playRounds(Game game, int rounds){
		for(int i=0;i<rounds*2;i++){
			game.endOfTurn();
		}
	}

	public static void changeOwnerOfCity(Game game, Position p){
		CityImpl c = (CityImpl) game.getCityAt(p);
		c.changeOwner();			
	}
}
