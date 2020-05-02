package model.actions.barActions;

import model.actions.Action;
import model.players.Player;

public class EatSnacksAction extends Action {
    public EatSnacksAction() {
        super("SNACK", "Eat some SNACKs", "Order and eat some peanuts or chips.", 5, -1,9, 4);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
        if(isUser) {
            System.out.println("You ate some unhealthy food at the counter.");
        }
        else {
            System.out.println(player.getName() + " ate some unhealthy food at the counter.");
        }
    }
}
