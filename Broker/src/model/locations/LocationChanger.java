package model.locations;

import model.players.Player;

public interface LocationChanger {
	
	public void moveTo(Player player, int locIdx);
	public Location getHomeObj();
	public Location getOfficeObj();
	public Location getParkObj();
	
}
