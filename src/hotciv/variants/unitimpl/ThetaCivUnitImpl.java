package hotciv.variants.unitimpl;

import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Unit;
import hotciv.variants.units.ThetaCivUnits;

public class ThetaCivUnitImpl implements Unit {

	String type;
	Player p;
	boolean skillInUse;
	boolean hasMoved;

	public ThetaCivUnitImpl(String type, Player p) {
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
		if(type.equals(GameConstants.ARCHER) && skillInUse|| type.equals(ThetaCivUnits.CHARIOT) && skillInUse || hasMoved){
			return 0;
		}else{
			return 1;
		}
	}

	public int getDefensiveStrength() {
		if(type.equals(GameConstants.ARCHER) && skillInUse){
			return 6;
		}else if(type.equals(GameConstants.ARCHER)|| type.equals(GameConstants.SETTLER)){
			return 3;
		}else if(type.equals(GameConstants.LEGION) || type.equals(ThetaCivUnits.CHARIOT) && skillInUse){
			return 2;
		}if(type.equals(ThetaCivUnits.CHARIOT)){
			return 1;
		}else{
			return 0;
		}
	}

	public int getAttackingStrength() {
		if(type.equals(GameConstants.ARCHER)){
			return 2;
		}else if(type.equals(GameConstants.LEGION)){
			return 4;
		}else if(type.equals(ThetaCivUnits.CHARIOT)){
			return 3;
		}else{
			return 0;
		}
	}

	public void setSkillInUse(){
		if(!skillInUse){
			skillInUse = true;
		}else if(skillInUse){
			skillInUse = false;
		}
	}
}
