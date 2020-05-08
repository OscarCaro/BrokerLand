package model.players.botStates.chooseActionStrategies;

import model.actions.Action;
import model.actions.moveActions.goOfficeAction;
import model.utils.Utils;

import java.util.List;

public class OfficePerformanceStrategy implements ChooseActionStrategy {
    @Override
    public Action chooseAction(List<Action> actions, List<Action> moveActions) {
        for (Action a : moveActions){
            if(a instanceof goOfficeAction) {
                return new goOfficeAction();
            }
        }
        return actions.get(Utils.randomNum(actions.size()));//in the office
    }
}
