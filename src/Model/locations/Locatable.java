package Model.locations;

import Model.locations.*;

public abstract class Locatable {
	protected Location loc;
	
	public Locatable(Location loc) {
		this.loc = loc;
	}
	
	public void go(Location newLoc) {
		this.loc = newLoc;
	}
	
	public boolean isIn (Location loc) {
		return loc.equals(this.loc);
	}
	

}
