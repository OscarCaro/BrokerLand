package controller;

import model.life.*;
import model.locations.*;
import model.players.Broker;
import model.players.Market;
import model.trading.*;

public class Game {

	public static Time t;
    
	private Map map;
    private Broker player;
    private Market market;

    public Game() {
        t = new Time();

    	map = new Map();
        market = new Market();
        market.initMarket();

        player = new Broker(map, market);        

    }

    public void showState() {
//        System.out.println(t + ". You're now at the "+ player.whereIs());
    }

    public void run() {
//        while (player.checkHealth()) {
//            Location current = player.whereIs();
//            showState();
//            current.menu(player);
//            player.askMove();
//            player.showLife();
//            Market.refresh();
//            player.refresh();
//        }
//        System.out.println(player.end());

    }

}
