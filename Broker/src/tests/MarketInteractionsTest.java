package tests;

import model.players.Bot;
import model.trading.Asset;
import model.trading.Market;
import org.junit.jupiter.api.Test;

class MarketInteractionsTest extends TestCommons {

    @Test
    void buy() {
        Market.getInstance().assets.clear();
        initGameAndMarket();
        Bot b = botDummy();
        b.buy();
        assertFalse(b.getPortfolio().isEmpty());
    }


    @Test
    void sell() {
        Market.getInstance().assets.clear();
        initGameAndMarket();
        Bot b = botDummy();
        b.buy();
        int testSold = b.getMoney();
        b.sell();
        assertTrue(testSold != b.getMoney());
    }

    @Test
    void refreshAsset() {
        Market.getInstance().assets.clear();
        initGameAndMarket();
        Bot b = botDummy();
        Asset a = new Asset();


        int i = a.price;

        a.refreshAsset(0.9); //happens when refreshing market
        a.buy(b, 1); //artificially pump the asset to be sure to get change
        a.decreaseBIndex(); //make sure it does not go bankrupt and disallows change
        int i1 = a.price;

        a.buy(b, 1);
        a.decreaseBIndex();
        a.refreshAsset(0.9);
        int i2 = a.price;

        a.buy(b, 1);
        a.decreaseBIndex();
        a.refreshAsset(0.9);
        int i3 = a.price;
        assertFalse(i == i2 && i == i1 && i1 == i2 && i == i3 && i1 == i3 && i2 == i3); //assert not equals undefined for arraylists apparently
    }
}