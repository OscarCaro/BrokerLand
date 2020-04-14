package model.locations;

import java.util.ArrayList;
import java.util.List;

import model.players.Player;

public class WorldMap implements LocationChanger{
	
	public static final int HOMEIDX = 0;
	public static final int OFFICEIDX = 1;
	public static final int PARKIDX = 2;
	
	private List<Location> locations;
	
	public WorldMap() {
		locations = new ArrayList<Location>();
		locations.add(HOMEIDX, new Home());
		locations.add(OFFICEIDX, new Office());
		locations.add(PARKIDX, new Park());		
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
