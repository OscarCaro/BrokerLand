package model.actions.moveActions;

import model.actions.Action;
import model.locations.WorldMap;
import model.players.Player;

public class goParkAction extends Action {
	
	public goParkAction() {
		super("park", "Go to the PARK", "Take a walk to the nearest park", 15, 2, 0);
	}

	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		player.getLocationChanger().moveTo(player, WorldMap.PARKIDX);
		if(!isUser) {
			System.out.println(player.getName() + " is now at a park.");
		}
	}

}
