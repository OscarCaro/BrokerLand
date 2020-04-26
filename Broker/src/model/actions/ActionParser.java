package model.actions;

import controller.Game;
import model.events.BrokerUpdateEvent;
import model.events.EventHandler;
import model.life.Time;
import model.players.Broker;

import java.util.List;

public class ActionParser {
	
	public static boolean parseAction (String input, List<Action> actions,
									   Broker player, boolean isUser) {
		
		// Search in list of actions given by location
		for (Action a : actions) {
			if (equal(a.getParsingName(), input)) {
				Time actionTime = Game.getTimeClone();
				actionTime.addTime(a.getTime());
				EventHandler.getInstance().addEvent(new BrokerUpdateEvent(actionTime, a, player));
				return true;
			}
		}
		
		// Not found in list, compare with "exit" or "help"	(common for every location)
		if (equal(input, "help")) {
			System.out.println("To select an action, write down the UPPERCASE part of its name");
			return true;
		}
		else if (equal(input, "exit")) {
			System.exit(0);
		}
		
		// No match found -> return false -> ask for input again
		return false;
	}
	
	private static boolean equal (String a, String b) {
		return a.toLowerCase().equals(b.toLowerCase());
	}

}
