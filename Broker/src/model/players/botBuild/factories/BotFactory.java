package model.players.botBuild.factories;

import controller.Difficulties.Difficulty;
import model.locations.WorldMap;
import model.players.Bot;
import model.players.Sex;
import model.utils.Utils;

public abstract class BotFactory {
	
	protected String name;
	protected String surname;
	protected int money;
	protected int initLocIdx;
	protected Sex sex;
	
	public abstract Bot generateBot(Difficulty diff);
	
	protected void setGenericAttributes() {
		sex = Sex.randomSex();
		this.name = Utils.generateName(sex);
    	this.surname = Utils.generateSurname();
    	this.money = 1000;
    	this.initLocIdx = WorldMap.HOMEIDX;
	}

}
