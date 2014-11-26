package hotciv.variants;

import hotciv.framework.strategy.UnitStrategy;

public class AlphaCivUnits implements UnitStrategy {

	public boolean getArcher() {
		return true;
	}

	public boolean getLegion() {
		return true;
	}

	public boolean getSettler() {
		return true;
	}

	public boolean getChariot() {
		return false;
	}
}
