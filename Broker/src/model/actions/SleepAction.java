package model.actions;

import model.players.Player;

public class SleepAction extends Action {
	
	public SleepAction() {
		super("Sleep", "Spend 6 hours getting a good hardworking person sleep.", 60*6, 5);
	}

	@Override
	protected void performSpecificAction(Player performer) {
		System.out.println("You got an ok rest.");
	}

}
