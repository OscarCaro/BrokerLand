package model.locations;

import controller.Game;
import model.actions.Action;
import model.actions.BuyAction;
import model.actions.CheckMarketAction;
import model.actions.SellAction;
import model.actions.StareWallAction;
import model.actions.WaitMarket;
import model.players.Broker;
import model.players.Market;

public class Office extends Location {

	public Office() {
    	super("Office");
    	addAction(new BuyAction());
    	addAction(new WaitMarket());
    	addAction(new SellAction());
    	addAction(new CheckMarketAction());
    	addAction(new StareWallAction());
    }

//    @Override
//    void enactAction(int i, Broker b) {
////        b.addMental(actions.get(i).mental);
////        Game.t.addTime(actions.get(i).time);
//        switch (i){
//            case 0:
//                Market.showState();
//                b.buy();
//                break;
//            case 1:
//                Market.opportunity();
//                Market.showState();
//                break;
//            case 2:
//                b.showAssets();
//                b.sell();
//                break;
//            case 3:
//                Market.showState();
//                break;
//            case 4:
//                System.out.println("You intently stare at the wall wondering about your life decisions.");
//                break;
//
//        }
//    }
}
