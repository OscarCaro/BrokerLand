package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.OfficePerformanceStrategy;
import model.players.botStates.socialStrategies.FocusedStrategy;

public class HardWorkingState extends BotState{
    public HardWorkingState() {
		super(new FocusedStrategy(), new OfficePerformanceStrategy());
	}

    @Override
    public void onStateChange(Bot b) {
        System.out.println(b.getName() + " wants to work!");
    }
}
