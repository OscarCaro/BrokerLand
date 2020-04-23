package controller;

import model.life.Time;
import model.players.Bot;
import model.players.Broker;
import model.trading.Market;
import model.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {

    public static Time t;

    private Broker player;
    private Market market;
    private List<Bot> bots;

    public Game() {
        t = new Time();
        market = Market.getInstance();
        player = new Broker();
        bots = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            bots.add(new Bot());
        }
    }

    public void run(){
        while (player.canContinue(true) && !playerIsWinner()) {
            player.update(); //comment this out if you want to check bots
            for (Bot b : bots) {
                b.update();
            }
            market.refresh();
            flushBots();
            System.out.println("\n");
        }
        System.out.println(player.endMessage());
    }

    private boolean playerIsWinner() {
        return bots.isEmpty();
    }

    private void flushBots() {
        Utils.equalsWall();
        Iterator<Bot> iter = bots.iterator();
        while (iter.hasNext()) {
            Bot b = iter.next();
            if (!b.canContinue(false)) {
                System.out.println(b.endMessage());
                System.out.println("----------------------" + b.getName() + " is OUT------------------------");
                iter.remove();
            }
        }
        System.out.println((bots.size()+1) + " total brokers remaining.");
        Utils.equalsWall();
    }
}
/*

 */