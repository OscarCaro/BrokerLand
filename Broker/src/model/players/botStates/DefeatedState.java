package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.DecadencyStrategy;
import model.players.botStates.socialStrategies.DepressedStrategy;

public class DefeatedState extends BotState {
	
    public DefeatedState() {
		super(new DepressedStrategy(), new DecadencyStrategy());
	}

    @Override
    public void onStateChange(Bot b) {
        System.out.println(b.getName() + " is having a crisis.");
    }
}
