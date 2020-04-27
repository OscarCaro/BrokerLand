package model.actions;

import model.players.Player;

public class ReturnLoanAction  extends Action {
    public ReturnLoanAction() {
        super("Pay", "PAY back a loan", "Pay back the remainder of the loan you have.", 20, -2, 0);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
        if(player.payBackLoan()) {
            if (isUser) {
                System.out.println("You successfully pay back your loan.");
            } else {
                System.out.println(player.getName() + " paid back his loan.");
            }
        }
        else{
            if (isUser) {
                System.out.println("You cannot pay back your loan.");
            } else {
                System.out.println(player.getName() + " is struggling to pay back his debt.");
            }
        }
    }
}