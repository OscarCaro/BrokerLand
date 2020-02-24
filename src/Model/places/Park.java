package model.places;

import controller.Game;
import model.trading.Broker;

public class Park extends Place {
    public static final int index = 2;
    public Park(){
        actions.add(new Action("Feed the pigeons", "Take a time off your stressful lifestyle feeding the pigeons.", 15, 4));
        actions.add(new Action("Observe the kids", "Look at the kids and reminisce of the times where you were one of them.", 30, 1));
        actions.add(new Action("Ride the swing", "Get on the swing and pretend you're a kid and life's not difficult.", 10, -10));
    }


    @Override
    void enactAction(int i, Broker b) {
        b.addMental(actions.get(i).mental);
        Game.time += actions.get(i).time;
        switch (i){
            case 0:
                System.out.println("You fed the pigeons wholesomely.");
                b.money -= 5;
                break;
            case 1:
                System.out.println("You look at the kids and wonder what happened.");
                break;
            case 2:
                System.out.println("You ride the swing, everyone stares at you, you go sit down on the bench again fully ashamed.");
                break;
        }
    }
}
