package model.players.socialStrategies;

import model.players.Bot;
import model.players.Player;

public class RoughStrategy implements SocialStrategy {

	@Override
	public void reactToGreeting(Bot self, Player other) {
		System.out.println(self.getName() + " cruelly ignored " + other.getName());			
	}
	
	@Override
	public String getMessageToSay() {
		return "Piss off!";
	}

}
