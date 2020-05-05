package model.players.botStates.chooseActionStrategies;

import model.actions.Action;
import model.utils.Utils;

import java.util.List;

public class DecadencyStrategy implements ChooseActionStrategy {
    @Override
    public Action chooseAction(List<Action> actions, List<Action> moveActions) {
        for (int i = 0; i<actions.size(); i++) {
            Action rand = actions.get(Utils.randomNum(actions.size()));
            if (rand.getMentalHealth() < 0 && Utils.randomNum(10)>5) {
                return rand;
            }
        }
        return moveActions.get(Utils.randomNum(moveActions.size()));
    }
}
