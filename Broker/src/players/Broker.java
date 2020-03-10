package players;

public class Broker extends Character {

	public Broker(LocationChanger map, Market globalMarktet) {
		super( map.getHome(), map, 1000, globalMarktet);
	}
}
