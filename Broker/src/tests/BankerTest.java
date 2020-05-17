package tests;

import model.players.Bot;
import model.players.Sex;
import model.players.marketstrategies.randomStrategy;
import model.trading.Banker;
import org.junit.jupiter.api.Test;

class BankerTest extends TestCommons {

    @Test
    void giveLoan() {
        initGameAndMarket();
        Bot b = botDummy();
        int aux = b.getMoney();
        b.takeLoan();
        assertTrue(aux < b.getMoney());
        assertTrue(b.hasLoan());
        assertTrue(Banker.getInstance().getLoansGiven() > 0);
    }

    @Test
    void savingLoan() {
        initGameAndMarket();
        Bot b = new Bot("", "", 0, -250, new randomStrategy(), 0.8, Sex.FEMALE);
        Banker.getInstance().savingLoan(b.getMoney(), b, false);
        assertTrue(b.hasLoan());
        assertTrue(b.getMoney() > 0);
    }
}