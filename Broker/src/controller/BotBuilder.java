package controller;

import java.util.ArrayList;
import java.util.List;

import model.players.Bot;
import model.players.marketstrategies.*;

public class BotBuilder {
	
    public static final int BOTSDEFAULTNUM = 50;
    public static final Difficulty DEFAULT_DIFFICULTY = Difficulty.NORMAL;
	
	private int botsNum;
	private Difficulty diff;	
	
	public BotBuilder(int num, Difficulty diff) {
		this.botsNum = num;
		this.diff = diff;
	}
	
	public List<Bot> build(){
		List<Bot> list = new ArrayList<>();
		
        for (int i = 0; i < botsNum * diff.aggressiveRatio; i++) {
            list.add(new Bot(new aggressiveStrategy()));
        }
        for (int i = 0; i < botsNum * diff.dumbassRatio; i++) {
        	list.add(new Bot(new dumbassStrategy()));
        }
        for (int i = 0; i < botsNum * diff.knowledgeableRatio; i++) {
        	list.add(new Bot(new knowledgeableStrategy()));
        }
        for (int i = 0; i < botsNum * diff.greedyRatio; i++) {
        	list.add(new Bot(new greedyStrategy()));
        }
        for (int i = 0; i < botsNum * diff.randomRatio; i++) {
        	list.add(new Bot(new randomStrategy()));
        }
        while (list.size() < botsNum) {
        	list.add(new Bot(new randomStrategy()));
        }        
		
		return list;
	}
	
}
