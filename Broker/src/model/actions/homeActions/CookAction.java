package model.actions.homeActions;

import model.actions.Action;
import model.players.Player;

public class CookAction extends Action {
	
	public CookAction() {
		super("Cook", "COOK meal", "Spend 2 hours cooking and eating food.", 60*2, 5,65);
	}
	
	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		if(isUser) {
			System.out.println("The meal was fine.");		
		}
		else {
			System.out.println( player.getName() + " ate at home");
		}		
	}

}
