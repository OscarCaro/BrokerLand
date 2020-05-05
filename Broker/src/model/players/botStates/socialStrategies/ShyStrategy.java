package model.players.botStates.socialStrategies;

import model.players.Bot;
import model.players.Player;

public class ShyStrategy implements SocialStrategy {

	@Override
	public void reactToGreeting(Bot self, Player other) {
		System.out.println(self.getName() + " blushed looking at " + other.getName());			
	}
	
	@Override
	public String getMessageToSay() {
		return "Hi!";
	}

}
