package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.SedentaryStrategy;
import model.players.botStates.socialStrategies.ShyStrategy;

public class LazyState extends BotState {
    public LazyState() {
		super(new ShyStrategy(), new SedentaryStrategy());
	}

    @Override
    public void onStateChange(Bot b) {
        System.out.println(b.getName() + " is feeling lazy.");
    }

}
