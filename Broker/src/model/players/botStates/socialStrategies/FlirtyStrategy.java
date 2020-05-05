package model.players.botStates.socialStrategies;

import model.players.Bot;
import model.players.Player;
import model.utils.Utils;

public class FlirtyStrategy implements SocialStrategy{
	
	private static String[] compliments = {
			"Did it hurt when you fell from heaven?",
			"Send me a picture so I can send Santa my wish list.",
			"Do you believe in love at first sight?"
	};

	@Override
	public void reactToGreeting(Bot self, Player other) {
		System.out.println(self.getName() + " winked the eye to " + other.getName());		
	}

	@Override
	public String getMessageToSay() {
		return compliments[Utils.randomNum(3)];
	}

}
