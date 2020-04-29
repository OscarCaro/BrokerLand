package model.locations;

import model.actions.barActions.EatSnacksAction;
import model.actions.barActions.GetADrinkAction;
import model.actions.barActions.OrderAMealAction;
import model.actions.barActions.TalkToTheBarmanAction;
import model.actions.moveActions.*;

public class Bar extends Location {

    public Bar() {
        super("Bar");
        addAction(new GetADrinkAction());
        addAction(new TalkToTheBarmanAction());
        addAction(new EatSnacksAction());
        addAction(new OrderAMealAction());
        addAction(new goHomeAction());
        addAction(new goOfficeAction());
        addAction(new goParkAction());
        addAction(new goBankAction());
    }

}
