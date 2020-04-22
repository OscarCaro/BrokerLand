package model.locations;

import java.util.ArrayList;
import java.util.List;
import model.players.Player;
import model.trading.Market;

public class WorldMap implements LocationChanger{
	
	private static WorldMap instance;
	
	public static final int HOMEIDX = 0;
	public static final int OFFICEIDX = 1;
	public static final int PARKIDX = 2;
	
	private List<Location> locations;
	
	private WorldMap() {
		locations = new ArrayList<Location>();
		locations.add(HOMEIDX, new Home());
		locations.add(OFFICEIDX, new Office());
		locations.add(PARKIDX, new Park());		
	}
	
	public static WorldMap getInstance() {
		if (instance == null) {
			instance = new WorldMap();
		}
		return instance;
	}

	@Override
	public void moveTo(Player player, int locIdx) {
		player.getCurrLoc().exitPlayer(player);			//Removes him from list of players inside old loc
		player.setCurrLoc(locations.get(locIdx)); 
		player.getCurrLoc().enterPlayer(player);		//Adds him to list of players inside new loc		
	}
	
	@Override
	public void startIn(Player player, int locIdx) {
		Location loc = locations.get(locIdx);
		player.setCurrLoc(loc);
		loc.enterPlayer(player);		
	}
	
	@Override
	public void printLocations() {
		for(int i = 0; i < locations.size(); i++) {
			System.out.println(i + ". " + locations.get(i));
		}		
	}
	
	@Override
	public int getNumOfLocs() {
		return this.locations.size();
	}
}
