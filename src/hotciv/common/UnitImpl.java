package hotciv.common;

import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Unit;

public class UnitImpl implements Unit {

	String type;
	Player p;
	boolean skillInUse;
	boolean hasMoved;

	public UnitImpl(String type, Player p) {
		this.type = type;
		this.p = p;
		skillInUse = false;
		hasMoved = false;
	}

	public String getTypeString() {
		return type;
	}

	public Player getOwner() {
		return p;
	}

	public boolean getIsSkillInUse(){
		return skillInUse;
	}
	
	public void setHasMoved(boolean moved){
		hasMoved = moved;
	}

	public int getMoveCount() {
		if(type.equals(GameConstants.ARCHER) && skillInUse || hasMoved){
			return 0;
		}else{
			return 1;
		}
	}

	public int getDefensiveStrength() {
		if(type.equals(GameConstants.ARCHER) && skillInUse){
			return 6;
		}else if(type.equals(GameConstants.ARCHER) || type.equals(GameConstants.SETTLER)){
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

	public String getAction(String unitType) {
		if(unitType.equals(GameConstants.SETTLER)){
			return GameConstants.BUILDCITY;
		}else if(unitType.equals(GameConstants.ARCHER)){	
			return GameConstants.FORTIFY;
		}else{
			return null;
		}
	}
	
	public void setSkillInUse(String action){
		if(action.equals(GameConstants.FORTIFY) && !skillInUse){
			skillInUse = true;
		}else if(action.equals(GameConstants.FORTIFY) && skillInUse){
			skillInUse = false;
		}
	}

}
