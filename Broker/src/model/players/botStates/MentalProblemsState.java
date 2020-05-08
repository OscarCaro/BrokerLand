package model.players.botStates;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.SeekMentalHealthStrategy;
import model.players.botStates.socialStrategies.FocusedStrategy;

public class MentalProblemsState extends BotState {
    public MentalProblemsState() {
		super(new FocusedStrategy(), new SeekMentalHealthStrategy());
	}

    @Override
    public void onStateChange(Bot b) {
        System.out.println(b.getName() + " has noticed about his lack of motivation.");
    }
}
