package model.actions.barActions;

import model.actions.Action;
import model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class InteractAction extends Action {
    public InteractAction() {
        super("Interact", "INTERACT with someone",
        		"Entablish a conversation with someone on this room.", 10, 1,0);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
    	List<Player> playersInside = new ArrayList<>(player.getCurrLoc().getPlayersInside());
    	if (playersInside.size() != 1) {
            playersInside.remove(player);
            Player other = player.choosePlayerToGreet(playersInside);
            String message = player.getMessageToSay();
            System.out.println(player.getName() + " said '" + message + "' to " + other.getName());
            other.reactToGreeting(player, message);
        }
    	else if(isUser) {
            System.out.println("Only the barman's here.");
        }
    	else{
    	    System.out.println(player.getName() + " is craving for some conversation.");
        }
    }
}
