package model.life;

public class Time {
	public static int minutes;
    public static int hours;
    public static int day;
    public Time(){
    	minutes = 0;
    	hours = 8;
    	day = 1;
    }
    
    public void addTime(int minutes) {
    	minutes += Math.abs(minutes);
    	while(minutes > 60) {
    		minutes -= 60;
    		hours +=1;
    	}
    	while(hours > 24) {
    		hours -= 24;
    		day +=1;
    	}
    }
    @Override
    public String toString() {
    	String aux1 = Integer.toString(hours);
    	if (aux1.length()<2) {
    		aux1 = "0".concat(aux1);
    	}
    	String aux2 = Integer.toString(minutes);
    	if (aux2.length()<2) {
    		aux2 = "0".concat(aux2);
    	}
    	return "It's: " +aux1 +  ":" + aux2 + " of day:" + day; 

    }
    
}
