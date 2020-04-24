package model.actions;

import model.players.Bot;
import model.players.Broker;
import model.players.Player;

public class SellAction extends Action {

	public SellAction() {
		super("Sell", "SELL", "Take a look at your portfolio and decide on what to sell.", 1*60, -2);
	}
	
	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		player.sell(); // Different for broker than for mods
	}

}
