package model.actions.moveActions;

import model.actions.Action;
import model.locations.WorldMap;
import model.players.Player;

public class goOfficeAction extends Action {
	
	public goOfficeAction() {
		super("office", "Go to the OFFICE", "Drive to the office.", 60, -1, 0, 0);
	}

	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		player.getLocationChanger().moveTo(player, WorldMap.OFFICEIDX);
		if(!isUser) {
			System.out.println(player.getName() + " is now at the office.");
		}
		else{
			System.out.println("You went to the Office.");
		}
	}

}
