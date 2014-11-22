package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class CityImpl implements City {

	private Player p;
	private String production;
	private int resources;

	public CityImpl(Player p) {
		this.p = p;
		resources = 0;
		production = "";
	}

	public Player getOwner() {
		return p;
	}

	public int getSize() {
		return 1;
	}

	public String getProduction() {
		return production;
	}
	
	public void changeOwner(){
		if(getOwner()==Player.RED){
			p = Player.BLUE;
		}else if(getOwner()==Player.BLUE){
			p = Player.RED;
		}
	}

	public void setProduction(String set) {
		production = set;
	}
	
	public int getResources(){
		return resources;
	}
	
	public void setResourcesPerRound(){
		resources += 6;
	}
	
	public boolean canDeductResources(String type){
		boolean canDeduct = false;
		if(type.equals(GameConstants.ARCHER) && getResources()>=10){
			canDeduct = true;
		}else if(type.equals(GameConstants.LEGION) && getResources()>=15){
			canDeduct = true;
		}else if(type.equals(GameConstants.SETTLER) && getResources()>=30){
			canDeduct = true;
		}
		return canDeduct;
	}
	
	public void deductResources(String type){
		if(type.equals(GameConstants.ARCHER)){
			resources -= 10;
		}else if(type.equals(GameConstants.LEGION)){
			resources -= 15;
		}else if(type.equals(GameConstants.SETTLER)){
			resources -= 30;
		}
	}

	@Override
	public String getWorkforceFocus() {
		// TODO Auto-generated method stub
		return null;
	}
}
