package hotciv.common;


public class UnitInfo {

	private int price;
	private int defensiveStrength;
	private int attackingStrength;

	/**
	 * Gives the UnitImpl a definition
	 * @param price the price of the unit
	 * @param defensiveStrength the defensive strength of the unit
	 * @param attackingStrength the attacking strength of the unit
	 */
	public UnitInfo(int price, int defensiveStrength, int attackingStrength) {
		this.price = price;
		this.defensiveStrength = defensiveStrength;
		this.attackingStrength = attackingStrength;
	}
	
	public int getPrice(){
		return price;
	}

	public int getDefensiveStrength() {
		return defensiveStrength;
	}

	public int getAttackingStrength() {
		return attackingStrength;
	}

}
