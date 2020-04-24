package model.actions;

import controller.Game;
import model.players.Bot;
import model.players.Broker;
import model.players.Player;

public abstract class Action{
	
	protected String parsingName;
    protected String name;
    protected String desc;
    protected int time, mental;


    public Action(String parsingName, String name, String desc, int time, int mental) {
    	this.parsingName = parsingName;
        this.name = name;
        this.desc = desc;
        this.time = time;
        this.mental = mental;
    }
    
    public void perform(Player player, boolean isUser) {
    	player.modifyHealth(mental);
    	//player.addTime(time);			To be used when we add more players (MODS)
    	Game.t.addTime(time);
    	performSpecificAction(player, isUser);
    }
    
    protected abstract void performSpecificAction(Player player, boolean isUser);

    @Override
    public String toString() {
        return name + ": " + desc;
    }
    
    public String getParsingName() {
    	return this.parsingName;
    }
}
