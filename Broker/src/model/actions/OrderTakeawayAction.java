package model.actions;

import model.players.Player;

public class OrderTakeawayAction extends Action {
	
	public OrderTakeawayAction() {
		super("Order takeaway", "Spend 1 hour ordering and eating takeaway food.", 60, -5);
	}

	@Override
	public void execute(Player performer) {
		// TODO Auto-generated method stub

	}

}
