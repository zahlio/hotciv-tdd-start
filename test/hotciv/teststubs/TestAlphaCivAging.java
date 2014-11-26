package hotciv.teststubs;

import static org.junit.Assert.assertEquals;
import hotciv.framework.strategy.AgingStrategy;
import hotciv.variants.aging.AlphaCivAging;

import org.junit.Before;
import org.junit.Test;

public class TestAlphaCivAging {

	private AgingStrategy ageStrategy;
	private int currentAge = 0;
	
	@Before
	public void setUp() {
		ageStrategy = new AlphaCivAging();
	}
	
	@Test
	public void TestAging(){
		assertEquals("Age should be 100", 100, ageStrategy.getNewAge(currentAge));
	}
}

