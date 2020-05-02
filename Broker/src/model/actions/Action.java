package model.actions;

import model.players.Player;

public abstract class Action{
	
	protected String parsingName;
    protected String name;
    protected String desc;
    protected int time, mental, hunger, money;


    public Action(String parsingName, String name, String desc, int time, int mental, int hunger, int money) {
    	this.parsingName = parsingName;
        this.name = name;
        this.desc = desc;
        this.time = time;
        this.mental = mental;
        this.hunger = (hunger == 0) ? -time/8 : hunger;
        this.money = money;
    }
    
    public void perform(Player player, boolean isUser) {
    	player.modifyHealth(mental);
    	player.addTime(time);
    	player.modifyHunger(hunger);
    	player.modifyMoney(-money);
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
    
    public int getTime() {
    	return this.time;
    }
}