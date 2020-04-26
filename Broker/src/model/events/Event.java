package model.events;

import model.life.Time;

public abstract class Event {
	
	private String title;
	private String description;
	private Time triggerTime;
	
	public Event(String title, String desc, Time triggerTime) {
		this.title = title;
		this.description = desc;
		this.triggerTime = triggerTime;
	}
	
	public abstract void execute();
	
	public Time getTime() {
		return new Time(triggerTime);
	}
}
