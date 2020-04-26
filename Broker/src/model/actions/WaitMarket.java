package model.actions;

import model.players.Player;

public class WaitMarket extends Action {
	
	public WaitMarket() {
		super("Wait", "WAIT for market move", "Spend 2 hours analysing the state of the market.", 2*60, -3,0);
	}
	
	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		if(isUser) {
			player.getGlobalMarket().opportunity();
			player.getGlobalMarket().print();
		}
		else {
			player.getGlobalMarket().opportunity();
			System.out.println(player.getName() + " analyzed the market.");
		}		
	}

}
