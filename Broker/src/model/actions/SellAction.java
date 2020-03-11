package model.actions;

import model.players.Player;

public class SellAction extends Action {

	public SellAction() {
		super("Sell", "Take a look at your portfolio and decide on what to sell.", 1*60, -2);
		
	}

	@Override
	protected void performSpecificAction(Player performer) {
		performer.sell();
	}

}
