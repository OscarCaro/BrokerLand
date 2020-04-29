package model.locations;

import model.actions.homeActions.CookAction;
import model.actions.homeActions.OrderTakeawayAction;
import model.actions.homeActions.SleepAction;
import model.actions.homeActions.TakeShowerAction;
import model.actions.moveActions.*;

public class Home extends Location {

	public Home() {
    	super("Home");
    	addAction(new SleepAction());
    	addAction(new CookAction());
    	addAction(new OrderTakeawayAction());
    	addAction(new TakeShowerAction());
    	addMoveAction(new goOfficeAction());
    	addMoveAction(new goParkAction());
    	addMoveAction(new goBankAction());
    	addMoveAction(new goBarAction());
    }
}