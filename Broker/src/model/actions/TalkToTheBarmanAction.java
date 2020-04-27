package model.actions;

import model.players.Player;

public class TalkToTheBarmanAction extends Action {
    public TalkToTheBarmanAction() {
        super("TALK", "TALK to the barman", "Tell the barman your problems as if he didn't have any of his own.", 15, 7,0);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
        if(isUser) {
            System.out.println("The barman puts up with your conversation until you leave him alone.\nHe nonetheless gave you some helpful tips.");
        }
        else {
            System.out.println(player.getName() + " is boring the barman to death.");
        }
    }
}
