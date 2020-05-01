package model.actions.moveActions;

import model.actions.Action;
import model.locations.WorldMap;
import model.players.Player;

public class goBarAction extends Action {
	
	public goBarAction() {
		super("bar", "Go to a BAR", "Get some fun at your favourite bar", 15, 1, 0);
	}

	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		player.getLocationChanger().moveTo(player, WorldMap.BARIDX);
		if(!isUser) {
			System.out.println(player.getName() + " is now at a bar.");
		}
		else{
			System.out.println("You went to the Bar.");
		}
	}

}
