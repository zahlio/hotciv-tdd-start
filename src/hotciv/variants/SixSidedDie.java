package hotciv.variants;

import hotciv.framework.strategy.DieStrategy;

import java.util.Random;

public class SixSidedDie implements DieStrategy {

	Random r = new Random();
	
	public int getDieValue() {
		return 1+r.nextInt(6);
	}

}
