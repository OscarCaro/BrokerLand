package model.actions;

import model.players.Bot;
import model.players.Broker;
import model.players.Player;

public class FeedPidgeonsAction extends Action {
	
	public FeedPidgeonsAction() {
		super("Feed", "FEED the pigeons", "Take a time off your stressful lifestyle feeding the pigeons.", 15, 4);
	}
	
	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		player.modifyMoney(-5);
		if(isUser) {
			System.out.println("You fed the pigeons wholesomely.");	
		}
		else {
			System.out.println( player.getName() + " fed the pigeons.");
		}		
	}

}
