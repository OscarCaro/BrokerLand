package model.life;

import javax.swing.text.DefaultEditorKit.CopyAction;

public class Time {
	
	public int minutes;
	public int hours;
	public int day;
	
	public Time() {
		minutes = 0;
		hours = 8;
		day = 1;
	}

	public Time(Time t) {
		copy(t);
	}

	public void addTime(int minutes) {
		this.minutes += Math.abs(minutes);
		while (this.minutes >= 60) {
			this.minutes -= 60;
			this.hours += 1;
		}
		while (this.hours >= 24) {
			this.hours -= 24;
			this.day += 1;
		}
	}
	
	public void copy(Time t) {
		this.day = t.day;
		this.hours= t.hours;
		this.minutes = t.minutes;
	}
	
	/**
	 * Returns the difference (in minutes) of the two times
	 */
	public int minus(Time t) {
		return this.totalMinutes() - t.totalMinutes();
	}
	
	public boolean isBeforeThan (Time t) {
		return this.minus(t) < 0;
	}

	@Override
	public String toString() {
		String aux1 = Integer.toString(hours);
		if (aux1.length() < 2) {
			aux1 = "0" + aux1;
		}
		String aux2 = Integer.toString(minutes);
		if (aux2.length() < 2) {
			aux2 = "0" + aux2;
		}
		return "It's: " + aux1 + ":" + aux2 + " of day " + day;

	}
	
	private int totalMinutes() {
		return this.day * 3600 + this.hours * 60 + this.minutes;
	}

}
