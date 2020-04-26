package model.events;

import model.actions.Action;
import model.life.Time;
import model.locations.Location;
import model.locations.LocationChanger;
import model.players.Bot;
import model.utils.Utils;

public class BotUpdateEvent extends Event{
	
	private Action action;
	private Location currLoc;
	private int newLocIdc;
	private LocationChanger map; 
	private Bot bot;

	public BotUpdateEvent(Time triggerTime, Action action, Location currLoc,
			int newLocIdx, LocationChanger map, Bot bot ) {
		super(triggerTime);
		this.action = action;
		this.currLoc = currLoc;
		this.newLocIdc = newLocIdx;
		this.map = map;
		this.bot = bot;
	}
	
	@Override
	public void execute() {
		Utils.minusWall();
		
		action.perform(bot, false);
		bot.setHasActionScheduled(false);
		
		if (Utils.randomNum(101) > 50 && currLoc != map.getLocation(newLocIdc)) {
			map.moveTo(bot, newLocIdc);                   	
            System.out.println(bot.getName() + " went to " + map.getLocation(newLocIdc) + ".");            
        }
	}
}
