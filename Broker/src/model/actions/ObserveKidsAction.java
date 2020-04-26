package model.actions;

import model.players.Player;

public class ObserveKidsAction extends Action {
	
	public ObserveKidsAction() {
		super("Observe", "OBSERVE the kids", "Look at the kids and reminisce of the times where you were one of them.", 30, 1,0);
	}
	
	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		if(isUser) {
			System.out.println("You look at the kids and wonder what happened.");		
		}
		else {
			System.out.println(player.getName() + " is in the park looking a bit sad.");
		}		
	}

}
