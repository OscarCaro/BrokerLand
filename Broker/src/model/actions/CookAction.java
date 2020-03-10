package model.actions;

import model.players.Player;

public class CookAction extends Action {
	
	public CookAction() {
		super("Cook meal", "Spend 2 hours cooking and eating food.", 60*2, 5);
	}

	@Override
	public void execute(Player performer) {
		// TODO Auto-generated method stub

	}

}
