package tests;

import model.actions.Action;
import model.actions.devActions.TestAction;
import model.actions.moveActions.goBankAction;
import model.actions.moveActions.goOfficeAction;
import model.locations.Location;
import model.players.Bot;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;

class ActionTest  extends TestCommons {


    @Test
    void performAction() {
        Bot b = botDummy();
        Action a = new TestAction("", "", "", 0, -10, -10,-10); //action decreases mental, money and hunger
        int mon = b.getMoney();
        int hun = b.getHunger();
        int men = b.getMental();
        a.perform(b,false);
        assertTrue(b.getMoney() != mon && b.getHunger() != hun && b.getMental() != men);
    }

    @Test
    void performMoveAction() {
        Bot b = botDummy();
        Action a = new goOfficeAction();
        a.perform(b,false);
        Location L = b.getCurrLoc();
        a = new goBankAction();
        a.perform(b,false);
        assertNotEquals(b.getCurrLoc(), L);
    }
}