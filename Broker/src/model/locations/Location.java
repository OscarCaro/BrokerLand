package model.locations;

import java.util.*;

import model.actions.Action;
import model.players.Broker;


public abstract class Location {
	
    protected List<Action> actions;
    public String name;   

    public Location(String name){
        actions = new ArrayList<>();
        this.name = name;
    }
    
    protected void addAction(Action action) {
    	actions.add(action);
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
//            enactAction(Integer.parseInt(aux), b);
        }
    }

//    abstract void enactAction(int i, Broker b);
    
    public String toString() {
    	return this.name;
    }

}
