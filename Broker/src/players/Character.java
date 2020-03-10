package players;

public abstract class Character {
	
	private Location currLoc;
	private LocationChanger map;
	private Market globalMarket;
	private MentalHealth mentalH;
	private int money;
	
	public Character(Location currLoc, LocationChanger map, int money, Market globalMarktet) {
		this.currLoc = currLoc;
		this.map = map;
		this.money = money;
		this.globalMarket = globalMarket;
		this.mentalH = new MentalHealth();
	}
	
	protected moveTo(int locIdx) {
		map.moveTo(this, locIdx);
	}
	
	

}
