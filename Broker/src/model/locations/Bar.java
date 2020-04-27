package model.locations;

import model.actions.barActions.EatSnacksAction;
import model.actions.barActions.GetADrinkAction;
import model.actions.barActions.OrderAMealAction;
import model.actions.barActions.TalkToTheBarmanAction;

public class Bar extends Location {

    public Bar() {
        super("Bar");
        addAction(new GetADrinkAction());
        addAction(new TalkToTheBarmanAction());
        addAction(new EatSnacksAction());
        addAction(new OrderAMealAction());
    }

}
