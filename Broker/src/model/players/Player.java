package model.players;

import model.life.MentalHealth;
import model.locations.Location;
import model.locations.LocationChanger;

public abstract class Player {
	
	protected Location currLoc;
	protected LocationChanger map;
	protected Market globalMarket;
	protected MentalHealth mentalH;
	protected int money;
	
	public Player(Location currLoc, LocationChanger map, int money, Market globalMarket) {
		this.currLoc = currLoc;
		this.map = map;
		this.money = money;
		this.globalMarket = globalMarket;
		this.mentalH = new MentalHealth(100);
	}
	
	protected void moveTo(int locIdx) {
		map.moveTo(this, locIdx);
	}
	
	public void setCurrLoc(Location newLoc) {
		this.currLoc = newLoc;
	}

}
