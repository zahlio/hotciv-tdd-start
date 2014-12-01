package hotciv.framework.common;

/**
 * this class is to be extended from the unit interface, so we dont change anything in units 
 */
public interface UnitAction {

	/**
	 * This method checks whether the current units skill is in use or not
	 * @return a boolean
	 */
	public boolean getIsSkillInUse();
	
	/**
	 * When a unit has moved it cant move again, so you have to set hasMoved to true or false if the round has ended
	 * @param moved send a boolean to determine if the unit has moved or not this round
	 */
	public void setHasMoved(boolean moved);

	/**
	 * when you use a skill then you set it to true, else you set it back to false
	 */
	public void setSkillInUse();
}
