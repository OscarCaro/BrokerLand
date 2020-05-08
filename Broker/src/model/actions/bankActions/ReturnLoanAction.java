package model.actions.bankActions;

import model.actions.Action;
import model.players.Player;
import model.players.Sex;

public class ReturnLoanAction  extends Action {
    public ReturnLoanAction() {
        super("Pay", "PAY back a loan", "Pay back the remainder of the loan you have.", 20, -2, 0, 0);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
        if(player.payBackLoan()) {
            if (isUser) {
                System.out.println("You successfully pay back your loan.");
            } else {
                System.out.println(player.getName() + " paid back "+ Sex.objectPronoun(player.getSex())+" loan.");
            }
        }
        else{
            if (isUser) {
                System.out.println("You cannot pay back your loan.");
            } else {
                System.out.println(player.getName() + " is struggling to pay back "+ Sex.objectPronoun(player.getSex())+" debt.");
            }
        }
    }
}