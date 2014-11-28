package hotciv.common;

import hotciv.framework.common.Player;
import hotciv.framework.common.Unit;

public class UnitImpl implements Unit {

	private Unit unit;

	public UnitImpl(Unit unit) {
		this.unit = unit;
	}

	public String getTypeString() {
		return unit.getTypeString();
	}

	public Player getOwner() {
		return unit.getOwner();
	}

	public int getMoveCount() {
		return unit.getMoveCount();
	}

	public int getDefensiveStrength() {
		return unit.getDefensiveStrength();
	}

	public int getAttackingStrength() {
		return unit.getAttackingStrength();
	}

	public boolean getIsSkillInUse() {
		return unit.getIsSkillInUse();
	}

	public void setHasMoved(boolean moved) {
		unit.setHasMoved(moved);
	}

	public void setSkillInUse() {
		unit.setSkillInUse();
	}
}