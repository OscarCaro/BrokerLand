package controller;

import model.life.*;
import model.locations.*;
import model.players.Broker;
import model.players.Market;
import model.players.Player;
import model.trading.*;

public class Game {

	public static Time t;
    
	private Map map;
    private Broker broker;
    private Market market;

    public Game() {
        t = new Time();

    	map = new Map();
        market = new Market();
        market.initMarket();

        broker = new Broker(map, market);        

    }

    public void run() {
        while (broker.canContinue()) {
        	broker.update();
        	market.refresh();
        	broker.refresh();
        	
        }
        System.out.println(broker.endMessage());
    }


}
