package tests;

import model.players.Bot;
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
}