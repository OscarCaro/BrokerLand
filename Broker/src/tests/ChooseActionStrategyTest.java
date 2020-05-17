package tests;

import model.actions.Action;
import model.locations.Home;
import model.locations.Location;
import model.players.botStates.chooseActionStrategies.ChooseActionStrategy;
import model.players.botStates.chooseActionStrategies.SeekFoodStrategy;
import model.players.botStates.chooseActionStrategies.SeekMentalHealthStrategy;
import org.junit.jupiter.api.Test;

class ChooseActionStrategyTest extends TestCommons {

	@Test
	void LocWithFoodTest() {
		ChooseActionStrategy chooseActionStrategy = new SeekFoodStrategy();
		Location home = new Home();
		Action action = chooseActionStrategy.chooseAction(home.getActions(), home.getMoveActions());
		assertTrue(action.getHunger() > 0);
	}

	@Test
	void LocWithMentalTest() {
		ChooseActionStrategy chooseActionStrategy = new SeekMentalHealthStrategy();
		Location home = new Home();
		Action action = chooseActionStrategy.chooseAction(home.getActions(), home.getMoveActions());
		assertTrue(action.getMentalHealth() > 0);
	}

}
