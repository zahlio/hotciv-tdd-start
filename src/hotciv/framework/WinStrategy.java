package hotciv.framework;


public interface WinStrategy {

	/** Figures out if someone has won the game, by figuring out what game is being played, then it chooses either age or the HashMap to define the winning strategy
	   * @param gameName insert current game which is being played
	   * @param age insert the gameImpl age
	   * @param cities insert a HashMap<Position,CityImpl>
	   * @return a player, null if no one has won.
	   */
	public Player getWinner(Game game);
	
	public void setAttackCount(Game game);
	
	public int getRedAttacks();
	
	public int getBlueAttacks();
}
