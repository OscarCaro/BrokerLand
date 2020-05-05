package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.SpendingStrategy;
import model.players.botStates.socialStrategies.FlirtyStrategy;

public class LuxuriousState implements  BotState {
    @Override
    public void updateSocialStrategy(Bot b) {
        b.setSocialStrategy(new FlirtyStrategy());
    }

    @Override
    public void updateActionStrategy(Bot b) {
        b.setChooseActionStrategy(new SpendingStrategy());
    }

    @Override
    public void update(Bot b) {
        System.out.println("Money has gone to " + b.getName() + "'s head.");
        updateActionStrategy(b);
        updateSocialStrategy(b);
    }
}
