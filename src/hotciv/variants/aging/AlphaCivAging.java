package hotciv.variants.aging;

import hotciv.framework.strategy.AgingStrategy;

public class AlphaCivAging implements AgingStrategy {

	public int getNewAge(int currentAge) {
		return currentAge+100;
	}
}

