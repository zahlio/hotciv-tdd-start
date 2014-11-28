package hotciv.throwable;

public class NotAUnitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2226164447709876065L;

	public NotAUnitException(String unit) {
		super(unit + " does not exist in this game");
	}
}
