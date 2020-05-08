package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.SeekFoodStrategy;
import model.players.botStates.socialStrategies.RoughStrategy;

public class HungerProblemsState extends BotState {

    public HungerProblemsState() {
		super(new RoughStrategy(), new SeekFoodStrategy());
	}

    @Override
    public void onStateChange(Bot b) {
        System.out.println(b.getName() + " has noticed about his unhealthy habits.");
    }
}
