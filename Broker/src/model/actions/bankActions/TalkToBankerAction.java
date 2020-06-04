package model.actions.bankActions;

import model.actions.Action;
import model.players.Player;

public class TalkToBankerAction extends Action {
    public TalkToBankerAction() {
        super("TALK", "TALK to the banker", "Try to start a casual conversation with the banker.", 15, -3,0, 0);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
        if(isUser) {
            System.out.println("You try talking with him but every chance he got he tried to swindle you into buying some insurance.\nIt does not seem like you can have a decent conversation with him.\nYou cut the chat short and walk away.");
        }
        else {
            System.out.println(player.getName() + " is trying to make the banker his psychologist.");
        }
    }
}
