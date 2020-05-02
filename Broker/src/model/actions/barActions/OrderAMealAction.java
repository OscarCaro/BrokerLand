package model.actions.barActions;

import model.actions.Action;
import model.players.Player;

public class OrderAMealAction extends Action {
    public OrderAMealAction() {
        super("MEAL", "Order a MEAL", "Get an unhealthy bar meal.", 1*60, 0,50, 25);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
        if(isUser) {
            System.out.println("You eat the so called food you ordered.");
        }
        else {
            System.out.println(player.getName() + " ate at the bar.");
        }
    }
}
