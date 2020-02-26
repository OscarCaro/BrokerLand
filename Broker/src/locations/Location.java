package locations;

public class Location {
	public String name; 
	private int id;
	Location(String name, int id){
		this.name = name;
		this.id = id;
	}
	
	boolean canBeGoneTo(Location loc) {
		return this.id != loc.id;
	}
	
	Location GoHome(){
		return new Home();
	}
}
