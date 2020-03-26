package model.actions;

import model.players.Bot;
import model.players.Broker;
import model.players.Player;

public class BuyAction extends Action {
	
	public BuyAction() {
		super("Buy", "Take a look at the market and decide on what to buy.", 1*60, -5);
	}

	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		player.buy(); // Different for broker that for mod	
	}

}
