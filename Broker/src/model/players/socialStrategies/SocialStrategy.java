package model.players.socialStrategies;

import model.players.Bot;
import model.players.Player;

public interface SocialStrategy {
	
	public String getMessageToSay();
	public void reactToGreeting(Bot self, Player other);

}
