package model.actions.barActions;

import model.actions.Action;
import model.players.Player;
import model.utils.Utils;

public class GetADrinkAction extends Action {
    public GetADrinkAction() {
        super("DRINK", "Get a DRINK", "Buy some alcohol and gulp it down.", 5, Utils.randomNum(10)>5 ? Utils.randomNum(7):-Utils.randomNum(7),1, 7);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
        if(isUser) {
            System.out.println("You gulp down some alcohol trying to forget who you are.");
        }
        else {
            System.out.println(player.getName() + " is drinking his problems away.");
        }
    }
}
