package hotciv.variants;

import hotciv.framework.AttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class EpsilonAttacking implements AttackStrategy{

	private int redAttacks = 0;
	private int blueAttacks = 0;

	//Attack win = 1, DefenceWin = 2, Equal Outcome = 0;
	public int performAttack(Game game, Position from, Position to) {
		Unit attackingUnit = game.getUnitAt(from);
		Unit defendingUnit = game.getUnitAt(to);
		Random r = new Random();
		int defensiveStrength = defendingUnit.getDefensiveStrength() + getFriendlySupport(game, to, defendingUnit.getOwner()) 
				* getTerrainFactor(game, to) * 1+r.nextInt(5);
		int attackingStrength = attackingUnit.getAttackingStrength() + getFriendlySupport(game, from, attackingUnit.getOwner()) 
				* getTerrainFactor(game, from) * 1+r.nextInt(5);
		System.out.println("The Defender has the strength of: " + defensiveStrength);
		System.out.println("The Attacker has the strength of: " + attackingStrength);

		//This defines the outcome
		if(defensiveStrength<attackingStrength && game.getPlayerInTurn()==Player.RED){
			redAttacks++;
			return 1;
		}
		else if(defensiveStrength<attackingStrength && game.getPlayerInTurn()==Player.BLUE){
			blueAttacks++;
			return 1;
		}
		else if(defensiveStrength>attackingStrength){
			return 2;
		}else{
			return 0;
		}

	}
	
	public int getRedAttacks(){
		return redAttacks;
	}
	
	public int getBlueAttacks(){
		return blueAttacks;
	}

	public static int getTerrainFactor(Game game, Position position) {
		// cities overrule underlying terrain
		if ( game.getCityAt(position) != null ) { return 3; }
		Tile t = game.getTileAt(position);
		if ( t.getTypeString() == GameConstants.FOREST ||
				t.getTypeString() == GameConstants.HILLS ) {
			return 2;
		} 
		return 1;
	}

	public static int getFriendlySupport(Game game, Position position, 
			Player player) {
		//The get8NeighbourhoodIterator is only called here
		Iterator<Position> neighborhood = get8NeighborhoodIterator(position);
		Position p; 
		int support = 0;
		while ( neighborhood.hasNext() ) {
			p = neighborhood.next();
			if ( game.getUnitAt(p) != null && 
					game.getUnitAt(p).getOwner() == player ) {
				support++;
			}
		}
		return support;
	}

	public static Iterator<Position> get8NeighborhoodIterator(Position center) {
		ArrayList<Position> list = new ArrayList<Position>();
		int row = center.getRow(); int col = center.getColumn();
		int r,c;
		for (int dr = -1; dr <= +1; dr++) {
			for (int dc = -1; dc <= +1; dc++) {
				r = row+dr; c = col+dc;
				// avoid positions outside the world
				if ( r >= 0 && r < GameConstants.WORLDSIZE &&
						c >= 0 && c < GameConstants.WORLDSIZE ) {
					// avoid center position
					if ( r != row || c != col ){
						list.add( new Position(r,c));
					}
				}
			}
		}
		return list.iterator();
	}
}
