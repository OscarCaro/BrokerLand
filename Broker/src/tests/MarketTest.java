package tests;

import controller.Difficulties.CustomDifficulty;
import controller.Game;
import model.trading.Market;
import org.junit.jupiter.api.Test;

class MarketTest extends TestCommons {
    @Test
    void marketSingletonCreationTest() {
        initGameAndMarket();
        assertFalse(Market.getInstance().assets.isEmpty());
    }

    @Test
    void canBuyAny() {
        initGameAndMarket();
        Market.getInstance().initAssets(10);
        assertTrue(Market.getInstance().canBuyAnyWith(1000));
    }

    @Test
    void minStart1() {
        Market.getInstance().assets.clear(); //if you run the class as test this will fix lying about the true outcome
        Game g = new Game(new CustomDifficulty(0, 0, 0, 0, 0, 0, 2, 5, 0, 0));
        assertEquals(5, Market.getInstance().assets.size());
    }

    @Test
    void minStart2() {
        Market.getInstance().assets.clear(); //if you run the class as test this will fix lying about the true outcome
        Game g = new Game(new CustomDifficulty(0, 0, 0, 0, 0, 0, 5, 2, 0, 0));
        assertEquals(5, Market.getInstance().assets.size());
    }
}
