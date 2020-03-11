package model.actions;

import model.players.Player;

public class FeedPidgeonsAction extends Action {
	
	public FeedPidgeonsAction() {
		super("Feed the pigeons", "Take a time off your stressful lifestyle feeding the pigeons.", 15, 4);
	}

	@Override
	protected void performSpecificAction(Player performer) {
		performer.modifyMoney(-5);
		System.out.println("You fed the pigeons wholesomely.");
	}

}
