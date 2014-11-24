package hotciv.framework.strategy;

public interface AgingStrategy {
	
	 /** sets the age of the next round
	   * @return the type as Integer.
	   */
	public int getNewAge(int currentAge);
}
