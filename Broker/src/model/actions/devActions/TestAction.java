package model.actions.devActions;

import model.actions.Action;
import model.players.Player;

public class TestAction extends Action {
    public TestAction(String s, String s1, String s2, int i, int i1, int i2, int i3) {
        super(s,s1,s2,i,i1,i2,i3);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
        System.out.println("Action performed.");
    }
}
