package model.places;

import controller.Game;
import model.trading.Broker;
import model.trading.Market;

public class Office extends Place {
    public static final int index = 1;
    public Office() {
        actions.add(new Action("Buy", "Take a look at the market and decide on what to buy.", 1*60, -5));
        actions.add(new Action("Wait for market move", "Spend 2 hours analysing the state of the market.", 2*60, -3));
        actions.add(new Action("Sell", "Take a look at your portfolio and decide on what to sell.", 1*60, -2));
        actions.add(new Action("Check the market", "Look at the current state of the market.", 1*60, -2));
        actions.add(new Action("Stare at wall", "Spend some time staring at the wall aimlessly.", 20, -5));
    }

    @Override
    void enactAction(int i, Broker b) {
        b.addMental(actions.get(i).mental);
        Game.t.addTime(actions.get(i).time);
        switch (i){
            case 0:
                Market.showState();
                b.buy();
                break;
            case 1:
                Market.opportunity();
                Market.showState();
                break;
            case 2:
                b.showAssets();
                b.sell();
                break;
            case 3:
                Market.showState();
                break;
            case 4:
                System.out.println("You intently stare at the wall wondering about your life decisions.");
                break;

        }
    }
}
