package model.players.botBuild;

import model.players.Bot;
import model.players.marketstrategies.*;

public class BotBuilder {
	
    private String name;
    private String surname;
    private int money;
    private int initLocIdx;
    private MarketStrategy strategy;
    
    public BotBuilder setName(String name, String surname) {
    	this.name = name;
    	this.surname = surname;
    	return this;
    }	
    
    public BotBuilder setStrategy(MarketStrategy strategy) {
    	this.strategy = strategy;
    	return this;
    }	
    
    public BotBuilder setMoney(int money) {
    	this.money = money;
    	return this;
    }
    
    public BotBuilder setInitLoc(int locIdx) {
    	this.initLocIdx = locIdx;
    	return this;
    }
    
    public Bot build() {
    	return new Bot(name, surname, initLocIdx, money, strategy);
    }
    
}
