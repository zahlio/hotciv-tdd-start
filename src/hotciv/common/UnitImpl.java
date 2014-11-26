package hotciv.common;

import hotciv.framework.common.GameConstants;
import hotciv.framework.common.Player;
import hotciv.framework.common.Unit;
import hotciv.framework.strategy.UnitStrategy;

public class UnitImpl implements Unit {

	String type;
	Player p;
	boolean skillInUse;
	boolean hasMoved;
	boolean archer, legion, settler, chariot = false;

	public UnitImpl(String type, Player p, UnitStrategy unitStrategy) {
		this.type = type;
		this.p = p;
		skillInUse = false;
		hasMoved = false;
		archer = unitStrategy.getArcher();
		legion = unitStrategy.getLegion();
		settler = unitStrategy.getSettler();
		chariot = unitStrategy.getChariot();
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
		if(type.equals(GameConstants.ARCHER) && skillInUse && archer || type.equals(GameConstants.CHARIOT) && skillInUse && chariot || hasMoved){
			return 0;
		}else{
			return 1;
		}
	}

	public int getDefensiveStrength() {
		if(type.equals(GameConstants.ARCHER) && skillInUse && archer){
			return 6;
		}else if(type.equals(GameConstants.LEGION) && skillInUse && legion){
			return 4;
		}else if(type.equals(GameConstants.ARCHER) && archer || type.equals(GameConstants.SETTLER) && settler){
			return 3;
		}else if(type.equals(GameConstants.LEGION) && legion){
			return 2;
		}if(type.equals(GameConstants.CHARIOT) && chariot){
			return 1;
		}
		else{
			return 0;
		}
	}

	public int getAttackingStrength() {
		if(type.equals(GameConstants.ARCHER) && archer){
			return 2;
		}else if(type.equals(GameConstants.LEGION) && legion){
			return 4;
		}else if(type.equals(GameConstants.CHARIOT) && chariot){
			return 3;
		}else{
			return 0;
		}
	}

	public String getAction(String unitType) {
		if(unitType.equals(GameConstants.SETTLER) && settler){
			return GameConstants.BUILDCITY;
		}else if(unitType.equals(GameConstants.ARCHER) && archer || unitType.equals(GameConstants.CHARIOT) && chariot){	
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
