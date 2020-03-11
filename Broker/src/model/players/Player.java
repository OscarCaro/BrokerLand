package model.players;


import model.life.MentalHealth;
import model.life.Time;
import model.locations.Location;
import model.locations.LocationChanger;
import model.trading.Market;

public abstract class Player {
	
	protected Location currLoc;
	protected LocationChanger map;
	protected Market globalMarket;
	protected MentalHealth mentalH;
	protected int money;
	protected Time ownTime;		//To be used when we add more players (MODS)
	
	public Player(LocationChanger map, int locIdx, int money, Market globalMarket) {
		map.startIn(this, locIdx);
		this.map = map;
		this.money = money;
		this.globalMarket = globalMarket;
		this.mentalH = new MentalHealth(100);
		this.ownTime = new Time();
	}
	
	public abstract void update();
	public abstract void buy();
	public abstract void sell();
	
	protected void moveTo(int locIdx) {
		map.moveTo(this, locIdx);
	}
	
	public boolean canContinue() {
		return !this.mentalH.insane() && money >= 0;
	}
	
	public void setCurrLoc(Location newLoc) {
		this.currLoc = newLoc;
	}
	
	public Location getCurrLoc() {
		return this.currLoc;
	}
	
	public void modifyHealth(int amount) {
		this.mentalH.add(amount);
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void modifyMoney(int amount) {
		this.money += amount;
	}
	
	public void addTime(int minutes) {
		this.ownTime.addTime(minutes);
	}
	
	public Market getGlobalMarket() {
		return this.globalMarket;
	}

}
