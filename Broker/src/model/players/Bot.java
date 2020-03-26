package model.players;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.locations.LocationChanger;
import model.locations.Map;
import model.trading.Market;
import model.utils.Utils;

public class Bot extends Player {

	public Bot(LocationChanger map, Market globalMarket) {
		super(Utils.generateName(), Utils.generateSurname(), map, Map.HOMEIDX, 1000, globalMarket);
	}

	@Override
	public void update() {
		askActions();
		askNewLocation();
	}
	
	private void askActions() {
		currLoc.performAction(this,	Utils.randomNum(currLoc.getNumOfActions()), false);
    }
    
	private void askNewLocation() {		        
        map.moveTo(this, Utils.randomNum(map.getNumOfLocs()));
        System.out.println(getName() + " went to " + currLoc);		// TODO: only when newLoc != oldLoc
	}

	@Override
	public void buy() {
		//TODO: complete
	}

	@Override
	public void sell() {
		//TODO: complete
	}
	


}
