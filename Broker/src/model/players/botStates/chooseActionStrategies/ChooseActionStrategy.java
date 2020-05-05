package model.players.botStates.chooseActionStrategies;

import model.actions.Action;

import java.util.List;

public interface ChooseActionStrategy {
	
	Action chooseAction(List<Action> actions, List<Action> moveActions);

}
