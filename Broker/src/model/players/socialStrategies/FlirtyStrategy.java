package model.players.socialStrategies;

import model.players.Bot;
import model.players.Player;

public class FlirtyStrategy implements SocialStrategy{

	@Override
	public void reactToGreeting(Bot self, Player other) {
		System.out.println(self.getName() + " winked the eye to" + other.getName());		
	}

	@Override
	public String getMessageToSay() {
		// TODO Auto-generated method stub
		return null;
	}

}
