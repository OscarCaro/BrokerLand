package model.life;

public class Time {
	public static int minutes;
	public static int hours;
	public static int day;

	public Time() {
		minutes = 0;
		hours = 8;
		day = 1;
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
		return "It's: " + aux1 + ":" + aux2 + " of day:" + day;

	}

}
