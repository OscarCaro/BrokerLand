package model.locations;

import model.actions.EatSnacksAction;
import model.actions.GetADrinkAction;
import model.actions.OrderAMealAction;
import model.actions.TalkToTheBarmanAction;

public class Bar extends Location {

    public Bar() {
        super("Bar");
        addAction(new GetADrinkAction());
        addAction(new TalkToTheBarmanAction());
        addAction(new EatSnacksAction());
        addAction(new OrderAMealAction());
    }

}
