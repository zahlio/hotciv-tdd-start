package hotciv.common;

import hotciv.framework.Game;
import hotciv.framework.Position;

public class ProduceUnit {
	
	private Game game;
	
	public ProduceUnit(Game game) {
		this.game = game;
	}

	public Position canProduceUnitAt(Position p){
		Position unitProduced = null;
		
		//if(((GameImpl)game).isInsideWorld(p)){
			unitProduced = unitCanBePlacedAt(p);
		//}
		return unitProduced;
	}

	private Position unitCanBePlacedAt(Position p){
		Position placeAtPosition = null;
		int i = 0;

		while(i<9){
			if(game.getUnitAt(placeUnitAt(p, i))==null){
				placeAtPosition = placeUnitAt(p, i);
				i=9;
			}else{
				i++;
			}
		}
		return placeAtPosition;
	}

	private Position placeUnitAt(Position p, int number){
		int[] placeX = {0,-1,-1,0,1,1,1,0,-1}; 
		int[] placeY = {0,0,1,1,1,0,-1,-1,-1};

		return new Position(p.getRow()+placeX[number],p.getRow()+placeY[number]);
	}
	
	public boolean accumulatedEnoughProduction(String unitType, Position p){
		boolean accumulateAndProduce = false;
		CityImpl c = (CityImpl) game.getCityAt(p);
		if(c.canDeductResources(unitType)){
				accumulateAndProduce = true;
			}
		return accumulateAndProduce;
	}
}
