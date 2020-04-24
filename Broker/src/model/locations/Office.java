package model.locations;

import controller.Game;
import model.actions.*;
import model.players.Broker;
import model.trading.Market;

public class Office extends Location {

	public Office() {
    	super("Office");
    	addAction(new BuyAction());
    	addAction(new WaitMarket());
    	addAction(new SellAction());
    	addAction(new CheckMarketAction());
    	addAction(new StareWallAction());
    }

}
