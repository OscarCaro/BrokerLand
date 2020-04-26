package model.actions;

import model.players.Player;

public class BuyAction extends Action {
	
	public BuyAction() {
		super("Buy", "BUY", "Take a look at the market and decide on what to buy.", 1*60, -5,0);
	}

	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		player.buy(); // Different for broker that for mod	
	}

}
