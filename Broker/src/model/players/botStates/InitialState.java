package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.FastMovingStrategy;
import model.players.botStates.socialStrategies.NormalStrategy;

public class InitialState extends BotState {
	
    public InitialState() {
		super(new NormalStrategy(), new FastMovingStrategy());
	}

    @Override
    public void onStateChange(Bot b) {
    	// No message because this is the first state (no change)
    }
}
