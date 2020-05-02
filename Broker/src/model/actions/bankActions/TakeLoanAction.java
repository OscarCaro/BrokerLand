package model.actions.bankActions;

import model.actions.Action;
import model.players.Player;

public class TakeLoanAction extends Action {
    public TakeLoanAction() {
        super("Take", "TAKE a loan", "Take a loan from the bank you will have to pay back.", 20, -4,0, 0);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
        if (player.takeLoan()) {
            if (!isUser) {
                System.out.println(player.getName() + " took out a loan.");
            }
        }
        else{
            if (!isUser) {
                System.out.println(player.getName() + " tried to cheat the bank to get another loan.");
            }
        }
    }
}
