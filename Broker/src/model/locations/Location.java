package model.locations;

import java.util.*;

import model.actions.Action;
import model.players.Broker;
import model.players.Player;


public abstract class Location {
	
    protected List<Action> actions;
    protected List<Player> playersInside;
    public String name;   

    public Location(String name){
        actions = new ArrayList<>();
        playersInside = new ArrayList<>();
        this.name = name;
    }
    
    protected void addAction(Action action) {
    	actions.add(action);
    }
    
    public void enterPlayer(Player player) {
    	this.playersInside.add(player);
    }
    
    protected void exitPlayer(Player player) {
    	this.playersInside.remove(player);
    }
    
    public void printActions() {
    	System.out.println("==================================================================");
    	for(int i = 0; i < actions.size(); i++) {
    		System.out.println(i + ". " + actions.get(i));
    	}
        System.out.println("==================================================================");
    }
    
    public void performAction(Player player, int actionIdx, boolean isUser) {
    	if(actionIdx >= 0 && actionIdx < actions.size()) {
            actions.get(actionIdx).perform(player, isUser);
        }
    	else throw new IllegalArgumentException("No such action by "+actionIdx + "in " + actions);
    }
    
    public int getNumOfActions() {
    	return this.actions.size();
    }
 
    public String toString() {
    	return this.name;
    }

}
