package model.players.marketstrategies;

import model.players.Bot;

public interface MarketStrategy {
    void buyAsset(Bot b);
    void sellAsset(Bot b);
}
