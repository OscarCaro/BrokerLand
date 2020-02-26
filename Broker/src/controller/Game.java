package controller;

import model.life.*;
import model.locations.*;
import model.places.*;
import model.trading.*;

import java.util.ArrayList;

public class Game {

	public static Time t;
    private ArrayList<Locatable> locatables;
    private ArrayList<Place> places;
    private Broker player;
    private Market market;

    public Game() {
        locatables = new ArrayList<>();
        places = new ArrayList<>();
        places.add(Home.index, new Home());
        places.add(Office.index, new Office());
        places.add(Park.index, new Park());
        player = new Broker();
        locatables.add(player);
        t = new Time();
        market = new Market();
        market.initMarket();
        locatables.add(market);

    }

    public void showState() {
        System.out.println(t + ". You're now at the "+ player.whereIs().getName());
    }

    public void run() {
        while (player.checkHealth()) {
            Place current = getPlace();
            showState();
            current.menu(player);
            player.askMove();
            player.showLife();
            Market.refresh();
            player.refresh();
            t.addTime(10);
        }
        System.out.println(player.end());

    }

    private Place getPlace() {
        for(int i = 0; i<places.size(); i++){
            if (player.isIn(Location.parse(i))){
                return places.get(i);
            }
        }
        return null;
    }
}
