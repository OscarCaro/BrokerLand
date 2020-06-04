package model.locations;

import model.actions.moveActions.*;
import model.actions.parkActions.FeedPidgeonsAction;
import model.actions.parkActions.ObserveKidsAction;
import model.actions.parkActions.RideSwingAction;
import model.actions.parkActions.TalkToKidsAction;

public class Park extends Location {
	
    public Park(){
    	super("Park");
    	addAction(new FeedPidgeonsAction());
    	addAction(new ObserveKidsAction());
    	addAction(new RideSwingAction());
    	addAction(new TalkToKidsAction());
    	addMoveAction(new goHomeAction());
    	addMoveAction(new goOfficeAction());
    	addMoveAction(new goBankAction());
    	addMoveAction(new goBarAction());
    }

}
