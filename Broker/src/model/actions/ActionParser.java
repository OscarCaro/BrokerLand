package model.actions;

import java.util.List;

public class ActionParser {
	
	public static Action parseAction (String input, List<Action> actions, List<Action> moveActions) {
		
		for (Action a : actions) {
			if (equal(a.getParsingName(), input)) {
				return a;
			}
		}
		
		for (Action a : moveActions) {
			if (equal(a.getParsingName(), input)) {
				return a;
			}
		}
		
		// Not found in list, compare with "exit" or "help"	(common for every location)
		if (equal(input, "help")) {
			System.out.println("To select an action, write down the UPPERCASE part of its name");
			return null;
		}
		else if (equal(input, "exit")) {
			System.exit(0);
		}
		
		// No match found -> return null -> ask for input again
		return null;
	}
	
	private static boolean equal (String a, String b) {
		return a.toLowerCase().equals(b.toLowerCase());
	}

}
