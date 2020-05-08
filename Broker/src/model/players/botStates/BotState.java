package model.players.botStates;

import java.util.List;

import model.actions.Action;
import model.players.Bot;
import model.players.Player;
import model.players.botStates.chooseActionStrategies.ChooseActionStrategy;
import model.players.botStates.socialStrategies.SocialStrategy;

public abstract class BotState {
	
    private SocialStrategy socialStrategy;
    private ChooseActionStrategy chooseActionStrategy;
	
	public BotState(SocialStrategy socialStrategy, ChooseActionStrategy chooseActionStrategy) {
		this.socialStrategy = socialStrategy;
		this.chooseActionStrategy = chooseActionStrategy;
	}
    
    /**
     * Print message on console to inform of changes of state
     */
    public abstract void onStateChange(Bot b);	
    
	public String getMessageToSay() {
		return socialStrategy.getMessageToSay();
	}
	
	public void reactToGreeting(Bot self, Player other) {
		socialStrategy.reactToGreeting(self, other);
	}
	
	public Action chooseAction(List<Action> actions, List<Action> moveActions) {
		return chooseActionStrategy.chooseAction(actions, moveActions);
	}
}
