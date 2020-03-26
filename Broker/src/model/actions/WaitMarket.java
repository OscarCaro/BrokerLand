package model.actions;

import model.players.Bot;
import model.players.Broker;
import model.players.Player;
import model.trading.Market;

public class WaitMarket extends Action {
	
	public WaitMarket() {
		super("Wait for market move", "Spend 2 hours analysing the state of the market.", 2*60, -3);
	}
	
	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		if(isUser) {
			player.getGlobalMarket().opportunity();
			player.getGlobalMarket().print();
		}
		else {
			player.getGlobalMarket().opportunity();
			System.out.println(player.getName() + " analized the market.");
		}		
	}

}
