package model.locations;

import java.util.*;

import model.actions.Action;
import model.actions.ActionParser;
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
    	for(Action a : actions) {
    		System.out.println("· " + a);
    	}
        System.out.println("==================================================================");
    }
    
    // For MODS: using random number
    public void performAction(Player player, int actionIdx) {
    	if(actionIdx >= 0 && actionIdx < actions.size()) {
            actions.get(actionIdx).perform(player, false);
        }
    }
    
    // For Broker (user): using input string
    public boolean performAction(Player player, String input) {
    	return ActionParser.parseAction(input, actions, player, true);
    }
    
    public int getNumOfActions() {
    	return this.actions.size();
    }
 
    public String toString() {
    	return this.name;
    }

}
