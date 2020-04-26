package model.players.marketstrategies;

import model.players.Bot;
import model.trading.Asset;
import model.utils.Pair;

public interface MarketStrategy {

    void buyAsset(Bot b);
    void sellAsset(Bot b);
}
