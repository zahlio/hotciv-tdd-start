package hotciv.civs;

import hotciv.teststubs.TestAlphaCivAging;
import hotciv.teststubs.TestAlphaCivWinStrategy;
import hotciv.teststubs.TestAlphaCivWorldLayout;
import hotciv.teststubs.TestBetaCivAging;
import hotciv.teststubs.TestBetaCivWinStrategy;
import hotciv.teststubs.TestDeltaCivWorldLayout;
import hotciv.teststubs.TestDieStub;
import hotciv.teststubs.TestEpsilonCivAttacking;
import hotciv.teststubs.TestEpsilonCivWinning;
import hotciv.teststubs.TestZetaCivAttacking;
import hotciv.teststubs.TestZetaCivWinStrategy;

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
	TestGammaCiv.class,
	TestZetaCivAttacking.class,
	TestZetaCivWinStrategy.class,
	TestDieStub.class
})
public class TestAll {

}