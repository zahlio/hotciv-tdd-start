package hotciv.variants.wincondition;

import hotciv.framework.common.City;
import hotciv.framework.common.Game;
import hotciv.framework.common.Player;
import hotciv.framework.common.Position;
import hotciv.framework.strategy.WinStrategy;

public class BetaCivWinCondition implements WinStrategy {

	private int redAttacks = 0;
	private int blueAttacks = 0;

	public Player getWinner(Game game){
		return betaCivWinStrategy(game);
	}
	
	public Player betaCivWinStrategy(Game game){
		boolean redHasCity = false;
		boolean blueHasCity = false;

		for(Position p : game.getCities().keySet()){
			City c = (City) game.getCityAt(p);
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
