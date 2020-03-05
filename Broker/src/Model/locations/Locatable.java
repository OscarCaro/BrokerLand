package model.locations;

import model.places.Place;

public abstract class Locatable {
	
	protected Place place;
	protected Locations possibleLocations;
	
	public Locatable() {
		this.possibleLocations = new Locations();		
	}
	
	public void go(Place newPlace) {
		this.place = newPlace;
	}
	
	public boolean isIn (Place place) {
		return this.place.equals(place);
	}
	
	public Place whereIs(){
		return place;
	}
}
