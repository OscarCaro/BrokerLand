package model.actions;

import model.players.Market;
import model.players.Player;

public class CheckMarketAction extends Action {
	
	public CheckMarketAction() {
		super("Check the market", "Look at the current state of the market.", 1*60, -2);
	}

	@Override
	protected void performSpecificAction(Player performer) {
		//TODO: re-implement once market is ok
		Market.showState();

	}

}
