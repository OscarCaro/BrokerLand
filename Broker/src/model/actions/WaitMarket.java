package model.actions;

import model.players.Market;
import model.players.Player;

public class WaitMarket extends Action {
	
	public WaitMarket() {
		super("Wait for market move", "Spend 2 hours analysing the state of the market.", 2*60, -3);
	}

	@Override
	protected void performSpecificAction(Player performer) {
		//TODO: re-implement once market is ok
		Market.opportunity();
		Market.showState();
	}

}
