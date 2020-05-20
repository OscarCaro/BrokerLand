package model.events;

import controller.Game;
import model.utils.SortedArrayList;
import model.utils.Utils;

import java.io.FileOutputStream;
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

    public void executeEvents(Game game) {
        FileOutputStream f = null;
        try {
            f = new FileOutputStream("src/skere.txt");
        Utils.minusWall();
        while (!eventList.get(0).stopHere() && eventList.size() > 1 && !game.noBotsRemaining()) {
            game.setTime(eventList.get(0).triggerTime);
            eventList.remove(0).execute();
            game.flushBots();
            game.marketprintout(f);
        }
        game.printScore();
        eventList.remove(0).execute(); // execute last event which should be player's and expect input
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
