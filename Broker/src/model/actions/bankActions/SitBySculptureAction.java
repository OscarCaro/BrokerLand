package model.actions.bankActions;

import model.actions.Action;
import model.players.Player;

public class SitBySculptureAction extends Action {
    public SitBySculptureAction() {
        super("sit", "SIT by the sculpture", "Sit, relax and try to absorb the money vibes of the ambient.", 10, -3,0, 0);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
        if(isUser) {
            System.out.println("You sit by the sculpture and gloat in your imagination.\n" +
                    "After a while you realise it is time to come back to reality.");
        }
        else {
            System.out.println(player.getName() + " is acting pensive at the bank.");
        }
    }
}
