package model.places;

import java.util.*;

import model.trading.Broker;


public abstract class Place {
	
    protected List<Action> actions;
    public String name;   

    public Place(String name){
        actions = new ArrayList<>();
        this.name = name;
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
    
    public String toString() {
    	return this.name;
    }

}
