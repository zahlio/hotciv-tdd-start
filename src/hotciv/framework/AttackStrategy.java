package hotciv.framework;

public interface AttackStrategy {

	/**
	 * This method performs an attack in moveUnit method
	 * @param game needs the current game data
	 * @param from the attacking unit
	 * @param to the defending unit
	 * @return 1 if attack wins, 2 if defence wins, 0 if there is equal outcome
	 */
	public int performAttack(Game game, Position from, Position to);
	
	public int getRedAttacks();
	
	public int getBlueAttacks();
}
