package model.locations;

import model.actions.barActions.EatSnacksAction;
import model.actions.barActions.GetADrinkAction;
import model.actions.barActions.InteractAction;
import model.actions.barActions.OrderAMealAction;
import model.actions.barActions.TalkToTheBarmanAction;
import model.actions.moveActions.*;

public class Bar extends Location {

    public Bar() {
        super("Bar");
        addAction(new GetADrinkAction());
        addAction(new TalkToTheBarmanAction());
        addAction(new InteractAction());
        addAction(new EatSnacksAction());
        addAction(new OrderAMealAction());
        addMoveAction(new goHomeAction());
        addMoveAction(new goOfficeAction());
        addMoveAction(new goParkAction());
        addMoveAction(new goBankAction());
    }

}
