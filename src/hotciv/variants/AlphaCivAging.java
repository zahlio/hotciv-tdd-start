package hotciv.variants;

import hotciv.framework.AgingStrategy;

public class AlphaCivAging implements AgingStrategy {

	public int getNewAge(int currentAge) {
		return currentAge+100;
	}
}

