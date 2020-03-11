package model.actions;

import model.players.Player;

public class StareWallAction extends Action {
	
	public StareWallAction() {
		super("Stare at wall", "Spend some time staring at the wall aimlessly.", 20, -5);
	}

	@Override
	protected void performSpecificAction(Player performer) {
		System.out.println("You intently stare at the wall wondering about your life decisions.");
	}

}
