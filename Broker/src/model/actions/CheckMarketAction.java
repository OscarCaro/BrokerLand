package model.actions;

import model.players.Player;

public class CheckMarketAction extends Action {
	
	public CheckMarketAction() {
		super("Check the market", "Look at the current state of the market.", 1*60, -2);
	}

	@Override
	public void execute(Player performer) {
		// TODO Auto-generated method stub

	}

}
