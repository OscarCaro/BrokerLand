package controller;

import java.util.ArrayList;
import java.util.List;

import model.life.*;
import model.locations.*;
import model.players.Bot;
import model.players.Broker;
import model.players.Player;
import model.trading.*;

public class Game {

	public static Time t;
    
	private Map map;
    private Broker broker;
    private Market market;
    private List<Bot> bots;

    public Game() {
        t = new Time();

    	map = new Map();
        market = new Market();

        broker = new Broker(map, market);  
        
        bots = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
        	bots.add(new Bot(map, market));
        }
    }

    public void run() {
        while (broker.canContinue()) {
        	broker.update();
        	for (Bot b : bots) {
        		b.update();
        		System.out.println("\n");
        	}
        	market.refresh(); 
        	System.out.println("\n\n\n");
        }
        System.out.println(broker.endMessage());
    }


}
