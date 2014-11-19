package hotciv.variants;

import hotciv.framework.AgingStrategy;

public class BetaCivAging implements AgingStrategy {

	public int getNewAge(int currentAge) {
		if(currentAge >= -4000 && currentAge < -100){
			return currentAge + 100;
		}else if(currentAge >= -100 && currentAge < -1){
			return currentAge + 99;
		}else if(currentAge >= 1 && currentAge < 50){
			return currentAge + 49;
		}else if(currentAge >= 50 && currentAge < 1750){
			return currentAge + 50;
		}else if(currentAge >= 1750 && currentAge < 1900){
			return currentAge + 25;
		}else if(currentAge >= 1900 && currentAge < 1970){
			return currentAge + 5;
		}else{
			return currentAge+1;
		}
	}
}
