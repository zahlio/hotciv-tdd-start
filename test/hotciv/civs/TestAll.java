 package hotciv.civs;

import hotciv.teststubs.TestAlphaCivAging;
import hotciv.teststubs.TestAlphaCivUnit;
import hotciv.teststubs.TestAlphaCivWinStrategy;
import hotciv.teststubs.TestAlphaCivWorldLayout;
import hotciv.teststubs.TestBetaCivAging;
import hotciv.teststubs.TestBetaCivWinStrategy;
import hotciv.teststubs.TestDeltaCivWorldLayout;
import hotciv.teststubs.TestDieStub;
import hotciv.teststubs.TestEpsilonCivAttacking;
import hotciv.teststubs.TestEpsilonCivWinning;
import hotciv.teststubs.TestGammaCivUnits;
import hotciv.teststubs.TestThetaCivUnit;
import hotciv.teststubs.TestThetaCivWithEpsilonStub;
import hotciv.teststubs.TestZetaCivAttacking;
import hotciv.teststubs.TestZetaCivWinStrategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestAlphaCiv.class,
	TestAlphaCivAging.class,
	TestAlphaCivUnit.class,
	TestAlphaCivWinStrategy.class,
	TestAlphaCivWorldLayout.class,
	TestBetaCiv.class,
	TestBetaCivAging.class,
	TestBetaCivWinStrategy.class,
	TestDeltaCivWorldLayout.class,
	TestEpsilonCivAttacking.class,
	TestEpsilonCivWinning.class,
	TestEpsilonCiv.class,
	TestGammaCiv.class,
	TestGammaCivUnits.class,
	TestThetaCiv.class,
	TestThetaCivUnit.class,
	TestThetaCivWithEpsilonStub.class,
	TestZetaCivAttacking.class,
	TestZetaCivWinStrategy.class,
	TestDieStub.class
})
public class TestAll {

}