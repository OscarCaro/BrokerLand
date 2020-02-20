package Model.locations;

public enum Location {
	HOME (0, "HOME"),
	OFFICE (1, "OFFICE");
	
	private int id;
	private String name;
	
	private Location(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public int getId() {
		return id;
	}
	
	public static void printAll() {
		System.out.println("-------------------------------------");
		for (Location loc : Location.values()) {
			 System.out.println(loc);
		}
		System.out.println("-------------------------------------");
	}
	public String toString() {
		return this.id + ": " + this.name + ".";
	}
	
	public static int totalValue() {
		int i = 0;
		for (Location loc : Location.values()) {
			i++;
		}
		return i;
	}
	
	public static Location parse(int id) {
		for (Location loc : Location.values()) {
			if(loc.id == id) {
				return loc;
			}
		}
		return null;
	}
}
