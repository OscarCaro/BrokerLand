package model.players.botBuild;

import java.util.ArrayList;
import java.util.List;

import controller.Difficulty;
import model.locations.WorldMap;
import model.players.Bot;
import model.players.marketstrategies.*;
import model.players.socialStrategies.FlirtyStrategy;
import model.players.socialStrategies.RoughStrategy;
import model.players.socialStrategies.ShyStrategy;
import model.players.socialStrategies.SocialStrategy;
import model.utils.Utils;

public class BotBuildDirector {
	
	public static final int BOTSDEFAULTNUM = 50;
    public static final Difficulty DEFAULT_DIFFICULTY = Difficulty.NORMAL;
    
    private BotBuilder builder;
    
    public BotBuildDirector(BotBuilder builder) {
    	this.builder = builder;
    }
	
	public List<Bot> build(int botsNum, Difficulty diff){
		List<Bot> list = new ArrayList<>();
		
		builder.setInitLoc(WorldMap.HOMEIDX).setMoney(1000);		// Common for all
		
        for (int i = 0; i < botsNum * diff.getAggressiveRatio(); i++) {
        	builder.setName(Utils.generateName(), Utils.generateSurname())
        	.setMarketStrategy(new aggressiveStrategy())
        	.setSocialStrategy(pickSocStr());
            list.add(builder.build());
        }
        
        for (int i = 0; i < botsNum * diff.getDumbassRatio(); i++) {
        	builder.setName(Utils.generateName(), Utils.generateSurname())
        	.setMarketStrategy(new dumbassStrategy())
        	.setSocialStrategy(pickSocStr());
            list.add(builder.build());
        }
        
        for (int i = 0; i < botsNum * diff.getKnowledgeableRatio(); i++) {
        	builder.setName(Utils.generateName(), Utils.generateSurname())
        	.setMarketStrategy(new knowledgeableStrategy())
        	.setSocialStrategy(pickSocStr());
            list.add(builder.build());
        }
        
        for (int i = 0; i < botsNum * diff.getGreedyRatio(); i++) {
        	builder.setName(Utils.generateName(), Utils.generateSurname())
        	.setMarketStrategy(new greedyStrategy())
        	.setSocialStrategy(pickSocStr());
            list.add(builder.build());
        }
        
        for (int i = 0; i < botsNum * diff.getRandomRatio(); i++) {
        	builder.setName(Utils.generateName(), Utils.generateSurname())
        	.setMarketStrategy(new randomStrategy())
        	.setSocialStrategy(pickSocStr());
            list.add(builder.build());
        }
        
        while (list.size() < botsNum) {
        	builder.setName(Utils.generateName(), Utils.generateSurname())
        	.setMarketStrategy(new randomStrategy())
        	.setSocialStrategy(pickSocStr());
            list.add(builder.build());
        }        
		
		return list;
	}
	
	private SocialStrategy pickSocStr() {
		switch (Utils.randomNum(3)) {
			case 0: return new ShyStrategy();
			case 1: return new FlirtyStrategy();
			default: return new RoughStrategy();
		}
	}
}
