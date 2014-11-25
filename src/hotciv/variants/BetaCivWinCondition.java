package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.framework.common.City;
import hotciv.framework.common.Game;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.strategy.WinStrategy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class BetaCivWinCondition implements WinStrategy {

	private int redAttacks = 0;
	private int blueAttacks = 0;
	
	public Player getWinner(Game game){
		return betaCivWinStrategy(game.getCities());
	}

	public Player betaCivWinStrategy(HashMap<Position, CityImpl> cities){
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
	
	//purely for statistical use
		public void setAttackCount(Game game) {
			if(game.getPlayerInTurn()==Player.RED){
				redAttacks++;
			}else{
				blueAttacks++;
			}
		}
		
		public int getRedAttacks(){
			return redAttacks;
		}
		
		public int getBlueAttacks(){
			return blueAttacks;
		}
}
