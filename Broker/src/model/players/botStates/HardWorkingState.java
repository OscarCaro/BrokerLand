package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.OfficePerformanceStrategy;
import model.players.botStates.socialStrategies.FocusedStrategy;

public class HardWorkingState implements BotState{
    @Override
    public void updateSocialStrategy(Bot b) {
        b.setSocialStrategy(new FocusedStrategy());
    }

    @Override
    public void updateActionStrategy(Bot b) {
        b.setChooseActionStrategy(new OfficePerformanceStrategy());
    }

    @Override
    public void update(Bot b) {
        System.out.println(b.getName() + " wants to work!");
        updateActionStrategy(b);
        updateSocialStrategy(b);
    }
}
