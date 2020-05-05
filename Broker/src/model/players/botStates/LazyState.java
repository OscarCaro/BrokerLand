package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.SedentaryStrategy;
import model.players.botStates.socialStrategies.ShyStrategy;

public class LazyState implements BotState {
    @Override
    public void updateSocialStrategy(Bot b) {
        b.setSocialStrategy(new ShyStrategy());
    }

    @Override
    public void updateActionStrategy(Bot b) {
        b.setChooseActionStrategy(new SedentaryStrategy());
    }

    @Override
    public void update(Bot b) {
        System.out.println(b.getName() + " is feeling lazy.");
        updateActionStrategy(b);
        updateSocialStrategy(b);
    }

}
