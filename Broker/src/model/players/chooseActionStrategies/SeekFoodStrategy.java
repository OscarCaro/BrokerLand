package model.players.chooseActionStrategies;

import java.util.List;

import model.actions.Action;
import model.utils.Utils;

public class SeekFoodStrategy implements ChooseActionStrategy {

	@Override
	public Action chooseAction(List<Action> actions, List<Action> moveActions) {
		for (Action a : actions) {
			if (a.getHunger() > 0) {
				return a;
			}
		}
		return moveActions.get(Utils.randomNum(actions.size()));
	}

}
