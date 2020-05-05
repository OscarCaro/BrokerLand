package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.SeekMentalHealthStrategy;
import model.players.botStates.socialStrategies.FocusedStrategy;

public class MentalProblemsState implements BotState {
    @Override
    public void updateSocialStrategy(Bot b) {
        b.setSocialStrategy(new FocusedStrategy());
    }

    @Override
    public void updateActionStrategy(Bot b) {
        b.setChooseActionStrategy(new SeekMentalHealthStrategy());
    }

    @Override
    public void update(Bot b) {
        System.out.println(b.getName() + " has noticed about his lack of motivation.");
        updateActionStrategy(b);
        updateSocialStrategy(b);
    }
}
