package model.events;

import controller.Game;
import model.utils.SortedArrayList;

import java.util.List;

public class EventHandler {

    // Since this is a singleton class an anybody can access it, the only public methods
    // should be the "addEvent" and the "executeEvents"

    private static EventHandler instance;

    private List<Event> eventList;

    private EventHandler() {
        eventList = new SortedArrayList<>((Event o1, Event o2) -> {
            if (o1.getTime().isBeforeThan(o2.getTime())) {
                return -1;
            } else {
                return 1;
            }
        });
    }

    public static EventHandler getInstance() {
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
        while ((!eventList.isEmpty()) && (eventList.get(0).getTime().isBeforeThan(Game.getTimeClone()))) {
            eventList.remove(0).execute();
        }
    }

}
