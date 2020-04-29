package model.locations;

import model.players.Player;

public interface LocationChanger {
	
	//Indexes kept in WorldMap class
	
	public void moveTo(Player player, int locIdx);
	public void startIn(Player player, int locIdx);	
}
