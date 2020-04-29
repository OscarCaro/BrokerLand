package model.locations;

import model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class WorldMap implements LocationChanger{
	
	private static WorldMap instance;
	
	public static final int HOMEIDX = 0;
	public static final int OFFICEIDX = 1;
	public static final int PARKIDX = 2;
	public static final int BANKIDX = 3;
	public static final int BARIDX = 4;
	private List<Location> locations;
	
	private WorldMap() {
		locations = new ArrayList<>();
		locations.add(HOMEIDX, new Home());
		locations.add(OFFICEIDX, new Office());
		locations.add(PARKIDX, new Park());
		locations.add(BANKIDX, new Bank());
		locations.add(BARIDX, new Bar());
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
}
