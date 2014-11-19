package hotciv.standard;

import static org.junit.Assert.assertEquals;
import hotciv.framework.AgingStrategy;
import hotciv.variants.AlphaCivAging;

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
