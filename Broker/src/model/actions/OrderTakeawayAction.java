package model.actions;

import model.players.Player;

public class OrderTakeawayAction extends Action {
	
	public OrderTakeawayAction() {
		super("Order takeaway", "Spend 1 hour ordering and eating takeaway food.", 60, -5);
	}

	@Override
	protected void performSpecificAction(Player performer) {
		System.out.println("The meal was just ok. What you'd expect from a takeaway meal.");
	}

}
