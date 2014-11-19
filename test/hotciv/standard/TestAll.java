package hotciv.standard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

	@RunWith(Suite.class)
	@Suite.SuiteClasses({
	   TestAlphaCiv.class,
	   TestAlphaCivAging.class,
	   TestAlphaCivWinStrategy.class,
	   TestAlphaCivWorldLayout.class,
	   TestBetaCiv.class,
	   TestBetaCivAging.class,
	   TestBetaCivWinStrategy.class,
	   TestDeltaCiv.class,
	   TestDeltaCivWorldLayout.class,
	   TestEpsilonCivAttacking.class,
	   TestEpsilonCivWinning.class,
	   TestGammaCiv.class   
	})
	public class TestAll {
		
	}
