package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.WinStrategy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class BetaCivWinCondition implements WinStrategy {

	//STUB SHOULD BE ABLE TO CALL THIS METHOD
	
	public Player getWinner(Game game){
		return BetaCivWinStrategy(game.getCities());
	}

	public Player BetaCivWinStrategy(HashMap<Position, CityImpl> cities){
		boolean redHasCity = false;
		boolean blueHasCity = false;
		Iterator<Entry<Position, CityImpl>> it = cities.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Position, CityImpl> pairs = it.next();
			City c = (City) pairs.getValue();
			if(c.getOwner()==Player.RED){
				redHasCity = true;
			}else if(c.getOwner()==Player.BLUE){
				blueHasCity = true;
			}
		}
		//THIS IS THE RETURN METHOD
		if(redHasCity && blueHasCity){
			return null;
		}else if(!redHasCity){
			return Player.BLUE;
		}else{
			return Player.RED;
		}
	}
}