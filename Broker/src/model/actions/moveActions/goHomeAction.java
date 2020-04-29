package model.actions.moveActions;

import model.actions.Action;
import model.locations.WorldMap;
import model.players.Player;

public class goHomeAction extends Action {
	
	public goHomeAction() {
		super("home", "Go HOME", "Drive home to take a rest", 60, 1, 0);
	}

	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		player.getLocationChanger().moveTo(player, WorldMap.HOMEIDX);
		if(!isUser) {
			System.out.println(player.getName() + " is now at home.");
		}
	}

}
