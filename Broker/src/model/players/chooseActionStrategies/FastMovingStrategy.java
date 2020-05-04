package model.players.chooseActionStrategies;

import java.util.List;

import model.actions.Action;
import model.utils.Utils;

public class FastMovingStrategy implements ChooseActionStrategy {

	@Override
	public Action chooseAction(List<Action> actions, List<Action> moveActions) {
		if (Utils.randomNum(100) > 30) {
			return moveActions.get(Utils.randomNum(actions.size()));
		}
		else {			
			return actions.get(Utils.randomNum(actions.size()));
		}	
	}

}
