package model.actions.parkActions;

import model.actions.Action;
import model.players.Player;

public class RideSwingAction extends Action {
	
	public RideSwingAction() {
		super("Ride", "RIDE the swing", "Get on the swing and pretend you're a kid and life's not difficult.", 10, -10,0);
	}
	
	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		if(isUser) {
			System.out.println("You ride the swing, everyone stares at you, you go sit down on the bench again fully ashamed.");		
		}
		else {
			System.out.println(player.getName() + " just embarrassed himself at the park");
		}		
	}

}
