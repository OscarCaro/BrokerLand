package model.locations;

import controller.Game;
import model.actions.Action;
import model.actions.FeedPidgeonsAction;
import model.actions.ObserveKidsAction;
import model.actions.RideSwingAction;
import model.players.Broker;

public class Park extends Location {
	
    public Park(){
    	super("Park");
    	addAction(new FeedPidgeonsAction());
    	addAction(new ObserveKidsAction());
    	addAction(new RideSwingAction());
    }


//    @Override
//    void enactAction(int i, Broker b) {
////        b.addMental(actions.get(i).mental);
////        Game.t.addTime(actions.get(i).time);
//        switch (i){
//            case 0:
//                System.out.println("You fed the pigeons wholesomely.");
////                b.money -= 5;
//                break;
//            case 1:
//                System.out.println("You look at the kids and wonder what happened.");
//                break;
//            case 2:
//                System.out.println("You ride the swing, everyone stares at you, you go sit down on the bench again fully ashamed.");
//                break;
//        }
//    }
}
