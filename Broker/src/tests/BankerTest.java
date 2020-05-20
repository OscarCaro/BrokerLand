package tests;

import model.players.Bot;
import model.players.Sex;
import model.players.marketstrategies.randomStrategy;
import model.trading.Banker;
import org.junit.jupiter.api.Test;

class BankerTest extends TestCommons {

    @Test
    void giveLoan() {
        initGameAndMarket(); //needed for time scale, else you will get NPE
        Bot b = botDummy();
        int aux = b.getMoney();
        b.takeLoan();
        assertTrue(aux < b.getMoney());
        assertTrue(b.hasLoan());
        assertTrue(Banker.getInstance().getLoansGiven() > 0);
    }

    @Test
    void savingLoan() {
        initGameAndMarket(); //needed for time scale, else you will get NPE
        Bot b = new Bot("", "", 0, -250, new randomStrategy(), 0.8, Sex.FEMALE);
        Banker.getInstance().savingLoan(b.getMoney(), b, false);
        assertTrue(b.hasLoan());
        assertTrue(b.getMoney() > 0);
    }
    @Test
    void loan12000Limit() {
        initGameAndMarket(); //needed for time scale, else you will get NPE
        Bot b = new Bot("", "", 0, -12050, new randomStrategy(), 0.8, Sex.FEMALE);
        Banker.getInstance().savingLoan(b.getMoney(), b, false);
        assertTrue(b.hasLoan());
        assertEquals(b.getMoney(), -50);
    }
}