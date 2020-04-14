package model.actions;

import model.players.Bot;
import model.players.Broker;
import model.players.Player;

public class RideSwingAction extends Action {
	
	public RideSwingAction() {
		super("Ride the swing", "Get on the swing and pretend you're a kid and life's not difficult.", 10, -10);
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
