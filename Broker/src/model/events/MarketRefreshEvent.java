package model.events;

import controller.Game;
import model.life.Time;
import model.trading.Market;

public class MarketRefreshEvent extends Event {
    private Game game;

    public MarketRefreshEvent(Time triggerTime, Game game) {
        super(triggerTime);
        this.game = game;
        stopHere = false;
    }

    @Override
    public void execute() {
        Market.getInstance().refresh();
        game.flushAssets();
        Time trigg = Game.getTimeClone();
        trigg.addTime(6*60);
        EventHandler.getInstance().addEvent(new MarketRefreshEvent(trigg, game));
    }
}
