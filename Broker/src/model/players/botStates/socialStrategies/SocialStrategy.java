package model.players.botStates.socialStrategies;

import model.players.Bot;
import model.players.Player;

public interface SocialStrategy {
	
	public String getMessageToSay();
	public void reactToGreeting(Bot self, Player other);

}
