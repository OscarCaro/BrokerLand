package model.players.botStates.chooseActionStrategies;

import model.actions.Action;
import model.utils.Utils;

import java.util.List;

public class SedentaryStrategy implements ChooseActionStrategy {
	
	@Override
	public Action chooseAction(List<Action> actions, List<Action> moveActions) {
		if (Utils.randomNum(100) > 30) {
			return actions.get(Utils.randomNum(actions.size()));
		}
		else {
			return moveActions.get(Utils.randomNum(moveActions.size()));
		}		
	}

}
