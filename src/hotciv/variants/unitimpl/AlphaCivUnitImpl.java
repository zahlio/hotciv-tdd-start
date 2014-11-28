package hotciv.variants.unitimpl;

import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Unit;

public class AlphaCivUnitImpl implements Unit{

	String type;
	Player p;
	boolean hasMoved;
	
	public AlphaCivUnitImpl(String type, Player p) {
		this.type = type;
		this.p = p;
		hasMoved = false;
	}

	public String getTypeString() {
		return type;
	}

	public Player getOwner() {
		return p;
	}

	public int getMoveCount() {
		if(hasMoved){
			return 0;
		}else{
			return 1;
		}
	}

	public int getDefensiveStrength() {
		if(type.equals(GameConstants.ARCHER)|| type.equals(GameConstants.SETTLER)){
			return 3;
		}else if(type.equals(GameConstants.LEGION)){
			return 2;
		}else{
			return 0;
		}
	}

	public int getAttackingStrength() {
		if(type.equals(GameConstants.ARCHER)){
			return 2;
		}else if(type.equals(GameConstants.LEGION)){
			return 4;
		}else{
			return 0;
		}
	}

	public boolean getIsSkillInUse() {
		return false;
	}

	public void setHasMoved(boolean moved) {
		hasMoved = moved;
	}

	public void setSkillInUse() {
	}
}
