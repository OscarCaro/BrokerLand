package model.actions;

import model.players.Player;

public class WaitMarket extends Action {
	
	public WaitMarket() {
		super("Wait for market move", "Spend 2 hours analysing the state of the market.", 2*60, -3);
	}

	@Override
	public void execute(Player performer) {
		// TODO Auto-generated method stub

	}

}
