package model.players.chooseActionStrategies;

import java.util.List;

import model.actions.Action;
import model.utils.Utils;

public class SedentatyStrategy implements ChooseActionStrategy {
	
	@Override
	public Action chooseAction(List<Action> actions, List<Action> moveActions) {
		if (Utils.randomNum(100) > 30) {
			return actions.get(Utils.randomNum(actions.size()));
		}
		else {
			return moveActions.get(Utils.randomNum(actions.size()));
		}		
	}

}
