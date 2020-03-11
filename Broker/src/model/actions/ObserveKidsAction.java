package model.actions;

import model.players.Player;

public class ObserveKidsAction extends Action {
	
	public ObserveKidsAction() {
		super("Observe the kids", "Look at the kids and reminisce of the times where you were one of them.", 30, 1);
	}

	@Override
	protected void performSpecificAction(Player performer) {
		System.out.println("You look at the kids and wonder what happened.");
	}

}
