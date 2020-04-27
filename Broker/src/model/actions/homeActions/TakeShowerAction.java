package model.actions.homeActions;

import model.actions.Action;
import model.players.Player;

public class TakeShowerAction extends Action {
	
	public TakeShowerAction() {
		super("Shower", "Take a SHOWER", "Spend 15 minutes taking a refreshing shower.", 15, 2,0);
	}
	
	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		if(isUser) {
			System.out.println("You relax while taking a hot shower.");		
		}
		else {
			System.out.println(player.getName() + " took a hot shower.");
		}		
	}

}
