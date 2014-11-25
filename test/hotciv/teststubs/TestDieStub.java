package hotciv.teststubs;

import static org.junit.Assert.*;
import hotciv.framework.strategy.DieStrategy;
import hotciv.variants.OneSidedDie;
import hotciv.variants.SixSidedDie;

import org.junit.Before;
import org.junit.Test;

public class TestDieStub {

	private DieStrategy staticDie;
	private DieStrategy intervalDie;
	
	@Before
	public void SetUp(){
		staticDie = new OneSidedDie();
		intervalDie = new SixSidedDie();
	}
	
	//Helper method for find die value
	private boolean isBetween0And6Inclusive(int input){
		if(input >= 1 && input <= 6){
			return true;
		}
		return false;
	}
	
	@Test
	public void oneSideDieShouldAlwaysReturn1(){
		assertEquals("This test should fail if die is not 1 every time", 1, staticDie.getDieValue());
	}
	
	@Test
	public void dieShouldHitBetween1InclusiveAnd6Inclusive(){
		assertTrue("if die is between 1 and 6, this will never return false", isBetween0And6Inclusive(intervalDie.getDieValue()));
	}
	
}
