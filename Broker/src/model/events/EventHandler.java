package model.events;

import model.life.Time;
import model.utils.SortedArrayList;
import model.utils.Utils;

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

    public Time executeEvents() {
        while (!eventList.get(0).stopHere() && eventList.size() > 1) {
            eventList.remove(0).execute();
        }
        Utils.minusWall();
        Time gameTime = eventList.get(0).getTime();
        eventList.remove(0).execute(); // execute last event which should be player's and expect input
        Utils.minusWall();
        return gameTime;
    }

}
