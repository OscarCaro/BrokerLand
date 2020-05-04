package model.players.chooseActionStrategies;

import java.util.List;

import model.actions.Action;
import model.utils.Utils;

public class SeekMentalHealthStrategy implements ChooseActionStrategy {

	@Override
	public Action chooseAction(List<Action> actions, List<Action> moveActions) {
		for (Action a : actions) {
			if (a.getMentalHealth() > 0) {
				return a;
			}
		}
		return moveActions.get(Utils.randomNum(actions.size()));
	}

}
