package model.players;


import model.locations.WorldMap;
import model.utils.Utils;

public class Bot extends Player {

	public Bot() {
		super(Utils.generateName(), Utils.generateSurname(), WorldMap.HOMEIDX, 1000);
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
		if(Utils.randomNum(101)>50) {
			map.moveTo(this, Utils.randomNum(map.getNumOfLocs()));
			System.out.println(getName() + " went to " + currLoc);        // TODO: only when newLoc != oldLoc
		}
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
