package hotciv.framework.strategy;

import hotciv.framework.Game;
import hotciv.framework.Player;


public interface WinStrategy {

	/** Figures out who is the winner
	   * @param game send the game as context
	   * @return a player, null if no one has won.
	   */
	public Player getWinner(Game game);
	
	/** counts one up for the player who attacked
	   * @param game send the game as context
	   */
	public void setAttackCount(Game game);
	
	/**
	 * @return how many times red has had a successful attack
	 */
	public int getRedAttacks();
	
	/**
	 * @return how many times blue has had a successful attack
	 */
	public int getBlueAttacks();
}
