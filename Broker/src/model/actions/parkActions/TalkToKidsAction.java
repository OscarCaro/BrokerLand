package model.actions.parkActions;

import model.actions.Action;
import model.players.Player;

public class TalkToKidsAction extends Action {

    public TalkToKidsAction() {
        super("Talk", "TALK to the kids", "Go towards the kids and try to talk with them about infantile stuff to escape reality.", 10, -12,0, 0);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
        if(isUser) {
            System.out.println("As soon as you try to approach the kids their parents stand in front of you and ask you to go away.\nYou sit back down in the bench as embarrassed as one can be.");
        }
        else {
            System.out.println(player.getName() + " is behaving like a pedophile in the park.");
        }
    }

}