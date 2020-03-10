package model.actions;

import model.players.Player;

public class SleepAction extends Action {
	
	public SleepAction() {
		super("Sleep", "Spend 6 hours getting a good hardworking person sleep.", 60*4, 5);
	}

	@Override
	public void execute(Player performer) {
		

	}

}
