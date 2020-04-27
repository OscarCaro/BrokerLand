package model.locations;

import model.actions.officeActions.*;

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
