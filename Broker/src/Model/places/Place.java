package model.places;

import model.trading.Broker;

import java.util.*;


public abstract class Place {
    protected List<Action> actions;

    public Place(){
        actions = new ArrayList<>();
    }
    public void menu(Broker b) {
        System.out.println("-------------------------------------");
        System.out.println("What do you want to do?");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ": " + actions.get(i));
        }
        System.out.println("-------------------------------------");
        String aux = Broker.in.nextLine();
        if (Integer.parseInt(aux) >= 0 && Integer.parseInt(aux) < actions.size()) {
            enactAction(Integer.parseInt(aux), b);
        }
    }

    abstract void enactAction(int i, Broker b);

}
