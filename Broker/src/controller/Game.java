package controller;

import model.life.*;
import model.locations.*;
import model.players.Broker;
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

        broker = new Broker(map, market);        

    }

    public void run() {
        while (broker.canContinue()) {
        	broker.update();
        	market.refresh(); 
        	System.out.println("\n\n\n");
        }
        System.out.println(broker.endMessage());
    }


}
