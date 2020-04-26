package model.events;

import model.actions.Action;
import model.life.Time;
import model.players.Broker;


public class BrokerUpdateEvent extends Event{

        private Action action;
        private Broker player;

    public BrokerUpdateEvent(Time triggerTime, Action action, Broker player) {
        super(triggerTime);
        this.action = action;
        this.player = player;
        this.stopHere = true;
    }

    @Override
    public void execute() {
        action.perform(player,true);
    }
}
