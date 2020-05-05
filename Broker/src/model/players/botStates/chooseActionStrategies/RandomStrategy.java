package model.players.botStates.chooseActionStrategies;

import model.actions.Action;
import model.utils.Utils;

import java.util.List;

public class RandomStrategy  implements ChooseActionStrategy {
    @Override
    public Action chooseAction(List<Action> actions, List<Action> moveActions) {
        if (Utils.randomNum(10)>=5){
            return actions.get(Utils.randomNum(actions.size()));
        }
        else{
            return moveActions.get(Utils.randomNum(moveActions.size()));
        }
    }
}
