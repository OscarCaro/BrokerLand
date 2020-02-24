package controller;

import model.locations.*;
import model.places.*;
import model.trading.*;

import java.util.ArrayList;

public class Game {

    public static int time;
    public static int day=1;
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

        market = new Market();
        market.initMarket();
        locatables.add(market);

        time = 8030;
    }

    public void showState() {
        System.out.println("Time: " + time%2400 + "  ----------------   " + player.whereIs().getName());
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
            time += 10;
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
