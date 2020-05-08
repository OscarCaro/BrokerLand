package model.players.botStates.socialStrategies;

import model.players.Bot;
import model.players.Player;
import model.players.Sex;

public class FocusedStrategy implements SocialStrategy {

    @Override
    public String getMessageToSay() {
        return "Hello there.";
    }

    @Override
    public void reactToGreeting(Bot self, Player other) {
        System.out.println(self.getName() + " looks like "+ Sex.subjectPronoun(self.getSex(), false)+"'s too concentrated to listen.");
    }
}
