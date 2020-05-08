package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.RandomStrategy;
import model.players.botStates.socialStrategies.NormalStrategy;

public class NormalState extends BotState {
    public NormalState() {
		super(new NormalStrategy(), new RandomStrategy());
	}

    @Override
    public void onStateChange(Bot b) {
        System.out.println(b.getName() + " is feeling okay.");
    }

}
