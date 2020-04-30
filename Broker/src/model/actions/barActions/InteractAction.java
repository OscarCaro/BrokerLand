package model.actions.barActions;

import java.util.List;

import model.actions.Action;
import model.players.Player;
import model.utils.Utils;

public class InteractAction extends Action {
    public InteractAction() {
        super("Talk", "Talk with someone",
        		"Entablish a conversation with someone on this room.", 10, 1,0);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
    	List<Player> playersInside = player.getCurrLoc().getPlayersInside();
    	Player other = player.choosePlayerToGreet(playersInside);
        other.reactToGreeting(player, player.getMessageToSay());
    }
}
