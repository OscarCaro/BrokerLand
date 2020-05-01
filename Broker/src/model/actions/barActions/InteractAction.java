package model.actions.barActions;

import java.util.ArrayList;
import java.util.List;

import model.actions.Action;
import model.players.Player;

public class InteractAction extends Action {
    public InteractAction() {
        super("Interact", "INTERACT with someone",
        		"Entablish a conversation with someone on this room.", 10, 1,0);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
    	List<Player> playersInside = new ArrayList<>(player.getCurrLoc().getPlayersInside());
    	playersInside.remove(player);
    	Player other = player.choosePlayerToGreet(playersInside);
    	String message = player.getMessageToSay();
    	System.out.println(player.getName() +" said '" + message + "' to " + other.getName() );
        other.reactToGreeting(player, message);
    }
}
