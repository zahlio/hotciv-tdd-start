package hotciv.variants;

import hotciv.framework.DieStrategy;

import java.util.Random;

public class SixSidedDie implements DieStrategy {

	Random r = new Random();
	
	public int dieValue() {
		return r.nextInt(5)+1;
	}

}
