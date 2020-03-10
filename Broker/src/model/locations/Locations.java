package model.locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import model.places.Home;
import model.places.Office;
import model.places.Park;
import model.places.Place;

public class Locations {
    
    private ArrayList<Place> places;
	
	public Locations() {
		places = new ArrayList<Place>();	
		places.add(new Home());
		places.add(new Office());
		places.add(new Park());
	}
	
	public Place getHome() {
		return places.get(0);
	}
	
	public Place getOffice() {
		return places.get(1);
	}
	
	public Place getPark() {
		return places.get(2);
	}
	
	public void printAll() {
		System.out.println("-------------------------------------");
		for (int i = 0; i < getSize(); i++) {
			System.out.println(i + ": " + places.get(i));
		}
		System.out.println("-------------------------------------");
	}
	
	public int getSize() {
		return places.size();
	}
	
	public Place parse(int idx) {
		return places.get(idx);
	}
	

}
