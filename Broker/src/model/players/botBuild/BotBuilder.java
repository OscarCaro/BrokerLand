package model.players.botBuild;

import model.players.Bot;
import model.players.Sex;
import model.players.marketstrategies.MarketStrategy;
import model.utils.Utils;

public class BotBuilder {
	
    private String name;
    private String surname;
    private int money;
    private int initLocIdx;
    private MarketStrategy marketStrategy;
    private double adaptability;
    private Sex sex;

    public BotBuilder generateName() {
    	this.name = Utils.generateName(sex);
    	this.surname = Utils.generateSurname();
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
    	return new Bot(name, surname, initLocIdx, money, marketStrategy, adaptability, sex);
    }

    public void setStateGovernorMutability(double adaptability) {
        this.adaptability = adaptability;
    }

    public BotBuilder generateSex() {
        sex = Sex.randomSex();
        return this;
    }
}
