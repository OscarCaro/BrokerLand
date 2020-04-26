package model.events;

import java.util.List;

import controller.Game;
import model.utils.SortedArrayList;

public class EventHandler {
	
	// Since this is a singleton class an anybody can access it, the only public methods
	// should be the "addEvent" and the "executeEvents"
	
	private static EventHandler instance;
	
	private List<Event> eventList;
	
	private EventHandler() {		
		eventList = new SortedArrayList<Event>( (Event o1, Event o2) -> {
			if (o1.getTime().isBeforeThan(o2.getTime())) {
				return -1;
			}
			else {
				return 1;
			}
		});
	}
	
	public EventHandler getInstance() {
		if (instance == null) {
			instance = new EventHandler();
		}
		return instance;
	}
	
	public void addEvent(Event e) {
		if (e != null) {
			eventList.add(e);
		}
	}
	
	public void executeEvents() {
		while ((eventList.size()>0) && (eventList.get(0).getTime().day == Game.t.day)) {
			eventList.remove(0).execute();
		}
	}

}
