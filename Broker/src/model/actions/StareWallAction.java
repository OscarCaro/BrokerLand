package model.actions;

import model.players.Bot;
import model.players.Broker;
import model.players.Player;

public class StareWallAction extends Action {
	
	public StareWallAction() {
		super("Stare", "STARE at wall", "Spend some time staring at the wall aimlessly.", 20, -5);
	}

	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		if(isUser) {
			System.out.println("You intently stare at the wall wondering about your life decisions.");		
		}
		else {
			System.out.println(player.getName() + " intently stares at the wall wondering about his life decisions.");		
		}		
	}
}
