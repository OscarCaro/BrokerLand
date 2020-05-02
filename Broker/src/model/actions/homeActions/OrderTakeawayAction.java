package model.actions.homeActions;

import model.actions.Action;
import model.players.Player;

public class OrderTakeawayAction extends Action {
	
	public OrderTakeawayAction() {
		super("Takeaway", "Order TAKEAWAY", "Spend 1 hour ordering and eating takeaway food.", 60, -5,60, 20);
	}
	
	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		if(isUser) {
			System.out.println("The meal was just ok. What you'd expect from a takeaway meal.");		
		}
		else {
			System.out.println(player.getName() + " ate a takeaway meal.");
		}		
	}

}
