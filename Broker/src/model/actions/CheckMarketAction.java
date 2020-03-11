package model.actions;

import model.players.Player;
import model.trading.Market;

public class CheckMarketAction extends Action {
	
	public CheckMarketAction() {
		super("Check the market", "Look at the current state of the market.", 1*60, -2);
	}

	@Override
	protected void performSpecificAction(Player performer) {
		performer.getGlobalMarket().print();
	}

}
