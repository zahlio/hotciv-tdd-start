package hotciv.common;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

	private UnitInfo unit;
	private String type;
	private Player owner;
	private boolean hasMoved, skillInUse;

	public UnitImpl(UnitInfo unit, String type, Player owner) {
		this.unit = unit;
		this.type = type;
		this.owner = owner;
	}

	public String getTypeString() {
		return type;
	}

	public Player getOwner() {
		return owner;
	}
	
	public int getPrice(){
		return unit.getPrice(); 
	}

	public int getMoveCount() {
		if(skillInUse || hasMoved){
			return 0;
		}else{
			return 1;
		}
	}

	public int getDefensiveStrength() {
		if(skillInUse){
			return unit.getDefensiveStrength() * 2;
		}
		return unit.getDefensiveStrength();
	}

	public int getAttackingStrength() {
		return unit.getAttackingStrength();
	}

	public boolean getIsSkillInUse() {
		return skillInUse;
	}

	public void setHasMoved(boolean moved) {
		hasMoved = moved;
	}

	public void setSkillInUse() {
		if(skillInUse){
			skillInUse = false;
		}else{
			skillInUse = true;
		}
	}
}