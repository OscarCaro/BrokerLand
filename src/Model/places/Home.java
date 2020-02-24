package model.places;

import controller.Game;
import model.trading.Broker;

public class Home extends Place {
    public static final int index = 0;
    public Home() {
        actions.add(new Action("Sleep", "Spend 6 hours getting a good hardworking person sleep.", 600, 5));
        actions.add(new Action("Cook meal", "Spend 2 hours cooking and eating food.", 200, 5));
        actions.add(new Action("Order takeaway", "Spend 1 hour ordering and eating takeaway food.", 100, -5));
        actions.add(new Action("Take a shower", "Spend 15 minutes taking a refreshing shower.", 15, 2));
    }

    @Override
    void enactAction(int i, Broker b) {
        b.addMental(actions.get(i).mental);
        Game.time += actions.get(i).time;
        switch (i){
            case 0:
                System.out.println("You got an ok rest.");
                break;
            case 1:
                System.out.println("The meal was fine.");
                break;
            case 2:
                System.out.println("The meal was just ok. What you'd expect from a takeaway meal.");
                break;
            case 3:
                System.out.println("You relax while taking a hot shower.");
                break;
        }
    }
}