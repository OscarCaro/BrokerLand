package model.locations;

import java.util.ArrayList;
import java.util.List;

import model.players.Player;

public class Map implements LocationChanger{
	
	public static final int HOMEIDX = 0;
	public static final int OFFICEIDX = 1;
	public static final int PARKIDX = 0;
	
	private List<Location> locations;
	
	public Map() {
		locations = new ArrayList<Location>();
		locations.add(HOMEIDX, new Home());
		locations.add(OFFICEIDX, new Office());
		locations.add(PARKIDX, new Park());		
	}

	@Override
	public void moveTo(Player player, int locIdx) {
		player.setCurrLoc(locations.get(locIdx)); 
		// TODO: remove player from list of prev loc and add to list of new
		
	}

	@Override
	public Location getHomeObj() {		
		return locations.get(HOMEIDX);
	}

	@Override
	public Location getOfficeObj() {		
		return locations.get(OFFICEIDX);
	}

	@Override
	public Location getParkObj() {
		return locations.get(PARKIDX);
	}
	

}
