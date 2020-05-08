package model.players.botStates.socialStrategies;

import model.players.Bot;
import model.players.Player;
import model.players.Sex;

public class DepressedStrategy implements SocialStrategy {
    @Override
    public void reactToGreeting(Bot self, Player other) {
        System.out.println(self.getName() + " looks like "+ Sex.subjectPronoun(self.getSex(), false)+" just wants to be alone.");
    }

    @Override
    public String getMessageToSay() {
        return "Do you sometimes think the rest of the world is out to get you?";
    }
}
