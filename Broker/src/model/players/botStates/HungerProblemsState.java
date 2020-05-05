package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.SeekFoodStrategy;
import model.players.botStates.socialStrategies.RoughStrategy;

public class HungerProblemsState implements BotState {

    @Override
    public void updateSocialStrategy(Bot b) {
        b.setSocialStrategy(new RoughStrategy());
    }

    @Override
    public void updateActionStrategy(Bot b) {
        b.setChooseActionStrategy(new SeekFoodStrategy());
    }

    @Override
    public void update(Bot b) {
        System.out.println(b.getName() + " has noticed about his unhealthy habits.");
        updateActionStrategy(b);
        updateSocialStrategy(b);
    }
}
