package model.actions.officeActions;

import model.actions.Action;
import model.players.Player;

public class CheckMarketAction extends Action {
	
	public CheckMarketAction() {
		super("Check", "CHECK the market", "Look at the current state of the market.", 1*60, -2,0);
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
