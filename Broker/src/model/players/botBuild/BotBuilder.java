package model.players.botBuild;

import model.players.Bot;
import model.players.botStates.chooseActionStrategies.ChooseActionStrategy;
import model.players.marketstrategies.*;
import model.players.botStates.socialStrategies.SocialStrategy;

public class BotBuilder {
	
    private String name;
    private String surname;
    private int money;
    private int initLocIdx;
    private MarketStrategy marketStrategy;
    private SocialStrategy socialStrategy;
    private ChooseActionStrategy chooseActionStrategy;
    private double adaptability;
    
    public BotBuilder setName(String name, String surname) {
    	this.name = name;
    	this.surname = surname;
    	return this;
    }	
    
    public BotBuilder setMarketStrategy(MarketStrategy marketStrategy) {
    	this.marketStrategy = marketStrategy;
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
    	return new Bot(name, surname, initLocIdx, money, marketStrategy, adaptability);
    }

    public void setStateGovernorMutability(double adaptability) {
        this.adaptability = adaptability;
    }
}
