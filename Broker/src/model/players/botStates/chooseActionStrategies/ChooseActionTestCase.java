package model.players.botStates.chooseActionStrategies;

import model.actions.Action;
import model.locations.Home;
import model.locations.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ChooseActionTestCase {

	@Test
	void LocWithFoodTest() {
		ChooseActionStrategy chooseActionStrategy = new SeekFoodStrategy();
		Location home = new Home();
		Action action = chooseActionStrategy.chooseAction(home.getActions(), home.getMoveActions());
		assertTrue(action.getHunger() > 0);		
	}

}
