package model.locations;

import java.util.*;

import model.actions.Action;
import model.players.Player;


public abstract class Location {
	
    protected List<Action> actions;			// Tasks to do in this location
    protected List<Action> moveActions;		// Actions to move to other locations
    protected List<Player> playersInside;
    public String name;   

    public Location(String name){
        actions = new ArrayList<>();
        moveActions = new ArrayList<>();
        playersInside = new ArrayList<>();
        this.name = name;
    }
    
    protected void addAction(Action action) {
    	actions.add(action);
    }
    
    protected void addMoveAction(Action action) {
    	moveActions.add(action);
    }
    
    public void enterPlayer(Player player) {
    	this.playersInside.add(player);
    }
    
    protected void exitPlayer(Player player) {
    	this.playersInside.remove(player);
    }
    
    public List<Action> getActions(){
    	return Collections.unmodifiableList(actions);
    }
    
    public List<Action> getMoveActions(){
    	return Collections.unmodifiableList(moveActions);
    }
 
    public String toString() {
    	return this.name;
    }

}
