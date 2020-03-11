package model.actions;

import model.players.Player;

public class TakeShowerAction extends Action {
	
	public TakeShowerAction() {
		super("Take a shower", "Spend 15 minutes taking a refreshing shower.", 15, 2);
	}

	@Override
	protected void performSpecificAction(Player performer) {
		System.out.println("You relax while taking a hot shower.");
	}

}
