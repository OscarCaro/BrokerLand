package tests;

import controller.Difficulties.CustomDifficulty;
import controller.Difficulties.PremadeDifficulty;
import controller.Game;
import model.trading.Asset;
import model.trading.Market;
import org.junit.jupiter.api.Test;

class MarketTest extends TestCommons {
    @Test
    void marketSingletonCreationTest() {
        Market.getInstance().assets.clear(); //if you run the class as test this will fix using an outdated market
        initGameAndMarket();
        assertFalse(Market.getInstance().assets.isEmpty());
    }

    @Test
    void canBuyAny() {
        Market.getInstance().assets.clear(); //if you run the class as test this will fix using an outdated market
        initGameAndMarket();
        Market.getInstance().initAssets(10);
        assertTrue(Market.getInstance().canBuyAnyWith(1000));
    }

    @Test
    void minStart1() {
        Market.getInstance().assets.clear(); //if you run the class as test this will fix using an outdated market
        Game g = new Game(new CustomDifficulty(0, 0, 0, 0, 0, 0, 2, 5, 0, 0));
        assertEquals(5, Market.getInstance().assets.size());
    }

    @Test
    void minStart2() {
        Market.getInstance().assets.clear(); //if you run the class as test this will fix using an outdated market
        Game g = new Game(new CustomDifficulty(0, 0, 0, 0, 0, 0, 5, 2, 0, 0));
        assertEquals(5, Market.getInstance().assets.size());
    }

    @Test
    void noNegativeAsset(){
        Market.getInstance().assets.clear(); //if you run the class as test this will fix using an outdated market
        new Game(PremadeDifficulty.WORLDTRADECENTER); //most volatile and populated market of premade dificulties
        boolean aux = true;
        for (int i = 0; i < 1000 && aux; i++){ //If it were to happen it would
            Market.getInstance().refresh();
            for (Asset a : Market.getInstance().assets) {
                if (a.price <= 0) {
                    aux = false;
                    break;
                }
            }
        }
        assertTrue(aux);
    }
}
