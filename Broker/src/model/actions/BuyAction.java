package model.actions;

import model.players.Player;

public class BuyAction extends Action {
	
	public BuyAction() {
		super("Buy", "Take a look at the market and decide on what to buy.", 1*60, -5);
	}

	@Override
	protected void performSpecificAction(Player performer) {
		performer.buy();
	}

}
