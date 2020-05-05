package model.players.botStates.socialStrategies;

import model.players.Bot;
import model.players.Player;

public class FocusedStrategy implements SocialStrategy {

    @Override
    public String getMessageToSay() {
        return "Hello there.";
    }

    @Override
    public void reactToGreeting(Bot self, Player other) {
        System.out.println(self.getName() + " looks like he's too concentrated to listen.");
    }
}
