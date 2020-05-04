package model.players.chooseActionStrategies;

import java.util.List;

import model.actions.Action;

public interface ChooseActionStrategy {
	
	public Action chooseAction(List<Action> actions, List<Action> moveActions);

}
