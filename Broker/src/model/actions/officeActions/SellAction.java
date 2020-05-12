package model.actions.officeActions;

import model.actions.Action;
import model.players.Player;

public class SellAction extends Action {

	public SellAction() {
		super("Sell", "SELL", "Take a look at your portfolio and decide on what to sell.", 20, -8,0, 0);
	}
	
	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		player.sell(); // Different for broker than for mods
	}

}
