package hotciv.teststubs;

import static org.junit.Assert.assertEquals;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.variants.aging.BetaCivAging;

import org.junit.Before;
import org.junit.Test;

public class TestBetaCivAging {

	private AgingStrategy ageStrategy;
	private int currentAge = -4000;

	@Before
	public void setUp() {
		ageStrategy = new BetaCivAging();
	}

	public int agingCalls(int times){
		for(int i=0; i<times; i++){
			currentAge = ageStrategy.getNewAge(currentAge);
		}
		return currentAge;
	}

	@Test
	public void AgeShouldBeYear2000BC(){
		assertEquals("Game year should be -2000", -2000, agingCalls(20));
	}

	@Test
	public void AgeShouldBeYear1BC(){
		assertEquals("Game year should be -1", -1, agingCalls(40));
	}

	@Test
	public void AgeShouldBeYear0BC(){
		assertEquals("Game year should be 0", 0, agingCalls(41));
	}

	@Test
	public void AgeShouldBeYear1AD(){
		assertEquals("Game year should be 1", 1, agingCalls(42));
	}

	@Test
	public void AgeShouldBeYear50AD(){
		assertEquals("Game year should be 50", 50, agingCalls(43));
	}

	@Test
	public void AgeShouldBeYear1400AD(){
		assertEquals("Game year should be 1400", 1400, agingCalls(70));
	}

	@Test
	public void AgeShouldBeYear1825AD(){
		assertEquals("Game year should be 1825", 1825, agingCalls(80));
	}

	@Test
	public void AgeShouldBeYear1930AD(){
		assertEquals("Game year should be 1930", 1930, agingCalls(89));
	}

	@Test
	public void AgeShouldBeYear2014AD(){
		assertEquals("Game year should be 2014", 2014, agingCalls(141));
	}
}
