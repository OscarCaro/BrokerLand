package model.actions.moveActions;

import model.actions.Action;
import model.locations.WorldMap;
import model.players.Player;

public class goBankAction extends Action {
	
	public goBankAction() {
		super("bank", "Go to the BANK", "Go to a bank to manage your loans.", 25, -1, 0, 0);
	}

	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		player.getLocationChanger().moveTo(player, WorldMap.BANKIDX);
		if(!isUser) {
			System.out.println(player.getName() + " is now at the bank.");
		}
		else{
			System.out.println("You went to the Bank.");
		}
	}

}
