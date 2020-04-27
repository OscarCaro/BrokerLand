package controller;

import model.events.EventHandler;
import model.life.Time;
import model.players.Bot;
import model.players.Broker;
import model.players.marketstrategies.*;
import model.trading.Market;
import model.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {


    public static Time t;
    private Broker player;
    private Market market;
    private List<Bot> bots;
    private EventHandler eventHandler;

    public Game(int botsNum, Difficulty diff) {
        t = new Time();
        market = Market.getInstance();
        player = new Broker();
        bots = new ArrayList<>();
        this.eventHandler = EventHandler.getInstance();
        if (diff == null) {
            for (int i = 0; i < botsNum; i++) {
                bots.add(new Bot());
            }
        } else {
            for (int i = 0; i < botsNum * diff.aggressiveRatio; i++) {
                bots.add(new Bot(new aggressiveStrategy()));
            }
            for (int i = 0; i < botsNum * diff.dumbassRatio; i++) {
                bots.add(new Bot(new dumbassStrategy()));
            }
            for (int i = 0; i < botsNum * diff.knowledgeableRatio; i++) {
                bots.add(new Bot(new knowledgeableStrategy()));
            }
            for (int i = 0; i < botsNum * diff.greedyRatio; i++) {
                bots.add(new Bot(new greedyStrategy()));
            }
            for (int i = 0; i < botsNum * diff.randomRatio; i++) {
                bots.add(new Bot(new randomStrategy()));
            }
            while (bots.size() < botsNum) {
                bots.add(new Bot());
            }
        }

    }

    public static Time getTimeClone() {
        return new Time(t);
    }

    public void run() {
        while (player.canContinue(true) && !playerIsWinner()) {
            player.update();
            for (Bot b : bots) {
                b.update();
            }
            flushBots();
            market.refresh();
            this.flushAssets();
            t = eventHandler.executeEvents();
        }
        System.out.println(player.endMessage());
    }


    private void flushAssets() {
        if (market.flushAssets()) {
            for (Bot b : bots) {
                b.flushAssets(false);
            }
            player.flushAssets(true);
        }
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
        System.out.println((bots.size() + 1) + " total brokers remaining.");
        Utils.equalsWall();
    }
}