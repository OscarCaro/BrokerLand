package model.locations;

import model.actions.Action;
import model.actions.CookAction;
import model.actions.OrderTakeawayAction;
import model.actions.SleepAction;
import model.actions.TakeShowerAction;
import model.players.Broker;

public class Home extends Location {

	public Home() {
    	super("Home");
    	addAction(new SleepAction());
    	addAction(new CookAction());
    	addAction(new OrderTakeawayAction());
    	addAction(new TakeShowerAction());
    }

//    @Override
//    void enactAction(int i, Broker b) {
//        b.addMental(actions.get(i).mental);
//        Game.t.addTime(actions.get(i).time);
//        switch (i){
//            case 0:
//                System.out.println("You got an ok rest.");
//                break;
//            case 1:
//                System.out.println("The meal was fine.");
//                break;
//            case 2:
//                System.out.println("The meal was just ok. What you'd expect from a takeaway meal.");
//                break;
//            case 3:
//                System.out.println("You relax while taking a hot shower.");
//                break;
//        }
//    }
}