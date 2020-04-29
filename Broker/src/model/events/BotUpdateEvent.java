package model.events;

import model.actions.Action;
import model.life.Time;
import model.locations.Location;
import model.locations.LocationChanger;
import model.players.Bot;
import model.utils.Utils;

public class BotUpdateEvent extends Event{
	
	private Action action;
	private Bot bot;

	public BotUpdateEvent(Time triggerTime, Action action, Bot bot) {
		super(triggerTime);
		this.action = action;
		this.bot = bot;
		this.stopHere = false;
	}
	
	@Override
	public void execute() {
		Utils.minusWall();
		action.perform(bot, false);
		bot.setHasActionScheduled(false);
	}
}
