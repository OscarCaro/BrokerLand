package model.players.chooseActionStrategies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.actions.Action;
import model.locations.Home;
import model.locations.Location;
import model.locations.WorldMap;
import model.players.Bot;
import model.players.marketstrategies.randomStrategy;
import model.players.socialStrategies.ShyStrategy;

class ChooseActionTestCase {

	@Test
	void LocWithFoodTest() {
		ChooseActionStrategy chooseActionStrategy = new SeekFoodStrategy();
		Location home = new Home();
		Action action = chooseActionStrategy.chooseAction(home.getActions(), home.getMoveActions());
		assertTrue(action.getHunger() > 0);		
	}

}
