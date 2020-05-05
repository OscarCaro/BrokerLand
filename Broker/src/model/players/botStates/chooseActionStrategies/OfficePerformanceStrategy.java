package model.players.botStates.chooseActionStrategies;

import model.actions.Action;
import model.actions.moveActions.goOfficeAction;
import model.utils.Utils;

import java.util.List;

public class OfficePerformanceStrategy implements ChooseActionStrategy {
    @Override
    public Action chooseAction(List<Action> actions, List<Action> moveActions) {
        if (moveActions.contains(new goOfficeAction())){
            return new goOfficeAction();
        }
        else{
            return actions.get(Utils.randomNum(actions.size()));
        }
    }
}
