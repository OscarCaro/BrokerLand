package model.events;

import model.life.Time;

public abstract class Event {
	
	private Time triggerTime;
	protected boolean stopHere;
	
	public Event(Time triggerTime) {
		this.triggerTime = triggerTime;
	}
	
	public abstract void execute();
	
	public Time getTime() {
		return new Time(triggerTime);
	}

	public  boolean stopHere(){
		return stopHere;
	}
}
