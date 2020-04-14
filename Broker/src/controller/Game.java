package controller;

import java.util.ArrayList;
import java.util.List;

import model.life.*;
import model.locations.*;
import model.players.Bot;
import model.players.Broker;
import model.trading.*;

public class Game {

	public static Time t;
    
	private WorldMap worldMap;
    private Broker player;
    private Market market;
    private List<Bot> bots;

    public Game() {
        t = new Time();
    	worldMap = new WorldMap();
        market = new Market();
        player = new Broker(worldMap, market);
        bots = new ArrayList<>();
        for(int i = 0; i < 10; i++) { //make difficulties?
        	bots.add(new Bot(worldMap, market));
        }
    }

    public void run() {
        while (player.canContinue()) {
        	player.update();
        	for (Bot b : bots) {
        		b.update();
        	}
        	market.refresh(); 
        	System.out.println("\n\n\n");
        }
        System.out.println(player.endMessage());
    }
}
