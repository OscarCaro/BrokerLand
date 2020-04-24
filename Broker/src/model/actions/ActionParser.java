package model.actions;

import java.util.List;

import model.players.Player;

public class ActionParser {
	
	public static boolean parseAction (String input, List<Action> actions, 
			Player player, boolean isUser) {
		
		// Search in list of actions given by location
		for (Action a : actions) {
			if (equal(a.getParsingName(), input)) {
				a.perform(player, isUser);
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
