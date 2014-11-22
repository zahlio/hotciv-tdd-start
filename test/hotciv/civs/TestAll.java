package hotciv.civs;

import hotciv.teststubs.TestAlphaCivAging;
import hotciv.teststubs.TestAlphaCivWinStrategy;
import hotciv.teststubs.TestAlphaCivWorldLayout;
import hotciv.teststubs.TestBetaCivAging;
import hotciv.teststubs.TestBetaCivWinStrategy;
import hotciv.teststubs.TestDeltaCivWorldLayout;
import hotciv.teststubs.TestEpsilonCivAttacking;
import hotciv.teststubs.TestEpsilonCivWinning;

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
	TestEpsilonCiv.class,
	TestGammaCiv.class   
})
public class TestAll {

}