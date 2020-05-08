package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.SpendingStrategy;
import model.players.botStates.socialStrategies.FlirtyStrategy;

public class LuxuriousState extends  BotState {
    public LuxuriousState() {
		super(new FlirtyStrategy(), new SpendingStrategy());
	}

    @Override
    public void onStateChange(Bot b) {
        System.out.println("Money has gone to " + b.getName() + "'s head.");
    }
}
