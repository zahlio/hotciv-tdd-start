package hotciv.framework.strategy;

public interface DieStrategy {

	/** returns the way the die outcome can occur
	   * @return the type as Integer.
	   */
	public int getDieValue();
}
