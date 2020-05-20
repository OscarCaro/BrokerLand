package controller;

import controller.Difficulties.Difficulty;
import model.events.EventHandler;
import model.events.MarketRefreshEvent;
import model.life.Time;
import model.players.Bot;
import model.players.Broker;
import model.players.botBuild.BotBuildDirector;
import model.players.botBuild.BotBuilder;
import model.trading.Asset;
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

    public Game(Difficulty diff) {
        t = new Time();
        market = Market.getInstance();
        market.initMarket(diff.getMarketStartingAssets(), diff.getMarketMinAssets(), diff.getMarketVolatilityRatio());
        player = new Broker();
        bots = new ArrayList<>();
        this.eventHandler = EventHandler.getInstance();
        this.bots = new BotBuildDirector(new BotBuilder()).build(diff.getDifficultyBotsNum(), diff);
        this.initBotRefreshes();
        this.initMarketRefreshes();
    }


    public static Time getTimeClone() {
        return new Time(t);
    }

    private void initBotRefreshes() {
        for (Bot b : bots) {
            b.update();
        }
    }

    public void run() {

        while (player.canContinue(true) && !playerIsWinner()) {
            //player.update();
            eventHandler.executeEvents(this);
        }
        System.out.println(player.endMessage());

    }

    public void printScore() {
        Utils.equalsWall();
        System.out.println((bots.size()) + " total brokers and you remain in business.");
        Utils.equalsWall();
    }


    private void initMarketRefreshes() {
        eventHandler.addEvent(new MarketRefreshEvent(getTimeClone(), this)); //periodical market refreshing event
    }


    public void flushAssets() {
        Asset a = market.bankruptAsset();
        if (a != null) {
            for (Bot b : bots) {
                b.flushAsset(a, false);
            }
            player.flushAsset(a, true);
            Market.getInstance().assets.remove(a);
            flushAssets();
        }
    }

    private boolean playerIsWinner() {
        return bots.isEmpty();
    }

    public void flushBots() {
        Iterator<Bot> iter = bots.iterator();
        while (iter.hasNext()) {
            Bot b = iter.next();
            if (!b.canContinue(false)) {
                System.out.println(b.endMessage());
                System.out.println("----------------------" + b.getName() + " is OUT------------------------");
                b.sellPortfolioOnDeath();
                iter.remove();
            }
        }
    }

    public void setTime(Time triggerTime) {
        t = triggerTime;
    }

    public boolean noBotsRemaining() {
        return bots.isEmpty();
    }
}