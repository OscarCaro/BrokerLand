package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.DecadencyStrategy;
import model.players.botStates.socialStrategies.DepressedStrategy;

public class DefeatedState implements BotState {
    @Override
    public void updateSocialStrategy(Bot b) {
        b.setSocialStrategy(new DepressedStrategy());
    }

    @Override
    public void updateActionStrategy(Bot b) {
        b.setChooseActionStrategy(new DecadencyStrategy());
    }

    @Override
    public void update(Bot b) {
        System.out.println(b.getName() + " is having a crisis.");
        updateActionStrategy(b);
        updateSocialStrategy(b);
    }
}
