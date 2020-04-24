package model.actions;

import model.players.Bot;
import model.players.Broker;
import model.players.Player;
import model.trading.Market;

public class CheckMarketAction extends Action {
	
	public CheckMarketAction() {
		super("Check", "CHECK the market", "Look at the current state of the market.", 1*60, -2);
	}

	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		if(isUser) {
			player.getGlobalMarket().print();		
		}
		else {
			System.out.println(player.getName() + " just checked the market");
		}		
	}

}
