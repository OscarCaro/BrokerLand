package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.FastMovingStrategy;
import model.players.botStates.socialStrategies.NormalStrategy;

public class InitialState implements BotState {
    @Override
    public void updateSocialStrategy(Bot b) {
        b.setSocialStrategy(new NormalStrategy());
    }

    @Override
    public void updateActionStrategy(Bot b) {
        b.setChooseActionStrategy(new FastMovingStrategy());
    }

    @Override
    public void update(Bot b) {
        updateActionStrategy(b);
        updateSocialStrategy(b);
    }
}
