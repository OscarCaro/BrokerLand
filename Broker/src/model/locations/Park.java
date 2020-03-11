package model.locations;

import model.actions.FeedPidgeonsAction;
import model.actions.ObserveKidsAction;
import model.actions.RideSwingAction;

public class Park extends Location {
	
    public Park(){
    	super("Park");
    	addAction(new FeedPidgeonsAction());
    	addAction(new ObserveKidsAction());
    	addAction(new RideSwingAction());
    }

}
