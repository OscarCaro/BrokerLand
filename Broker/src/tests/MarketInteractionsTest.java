package tests;

import model.players.Bot;
import model.trading.Asset;
import model.trading.Market;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

class MarketInteractionsTest extends TestCommons {

    @Test
    void buy() {
        initGameAndMarket();
        Bot b = botDummy();
        b.buy();
        assertFalse(b.getPortfolio().isEmpty());
    }


    @Test
    void sell() {
        initGameAndMarket();
        Bot b = botDummy();
        b.buy();
        int testSold = b.getMoney();
        b.sell();
        assertTrue(testSold != b.getMoney());
    }

    @Test
    void refreshAsset() {
        initGameAndMarket();
        ArrayList<Integer> ints = new ArrayList<>();
        for (Asset A : Market.getInstance().assets) {
            ints.add(A.price);
        }
        Market.getInstance().refresh();
        ArrayList<Integer> intsAfter = new ArrayList<>();
        for (Asset A : Market.getInstance().assets) {
            intsAfter.add(A.price);
        }
        assertFalse(ints.equals(intsAfter));//assert not equals undefined for arraylists apparently
    }
}