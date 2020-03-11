package model.locations;

import model.players.Player;

public interface LocationChanger {
	
	public void moveTo(Player player, int locIdx);
	public void startIn(Player player, Location loc);
	public void printLocations();
	public int getNumOfLocs();
	public Location getHomeObj();
	public Location getOfficeObj();
	public Location getParkObj();
	
}
