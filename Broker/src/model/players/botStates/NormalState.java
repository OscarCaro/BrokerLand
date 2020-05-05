package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.RandomStrategy;
import model.players.botStates.socialStrategies.NormalStrategy;

public class NormalState implements BotState {
    @Override
    public void updateSocialStrategy(Bot b) {
        b.setSocialStrategy(new NormalStrategy());
    }

    @Override
    public void updateActionStrategy(Bot b) {
        b.setChooseActionStrategy(new RandomStrategy());
    }

    @Override
    public void update(Bot b) {
        System.out.println(b.getName() + " is feeling okay.");
        updateActionStrategy(b);
        updateSocialStrategy(b);
    }

}
