package controller;

import java.util.ArrayList;

import model.life.*;
import model.locations.*;
import model.places.*;
import model.trading.*;

public class Game {

	public static Time t;
    
    private Broker player;
    private Market market;

    public Game() {
        
        player = new Broker();
        t = new Time();
        market = new Market();
        market.initMarket();

    }

    public void showState() {
        System.out.println(t + ". You're now at the "+ player.whereIs());
    }

    public void run() {
        while (player.checkHealth()) {
            Place current = player.whereIs();
            showState();
            current.menu(player);
            player.askMove();
            player.showLife();
            Market.refresh();
            player.refresh();
        }
        System.out.println(player.end());

    }

}
