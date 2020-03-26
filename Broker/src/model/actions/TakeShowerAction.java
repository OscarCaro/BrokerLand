package model.actions;

import model.players.Bot;
import model.players.Broker;
import model.players.Player;

public class TakeShowerAction extends Action {
	
	public TakeShowerAction() {
		super("Take a shower", "Spend 15 minutes taking a refreshing shower.", 15, 2);
	}
	
	@Override
	protected void performSpecificAction(Player player, boolean isUser) {
		if(isUser) {
			System.out.println("You relax while taking a hot shower.");		
		}
		else {
			System.out.println(player.getName() + " takes a hot shawer.");		
		}		
	}

}
