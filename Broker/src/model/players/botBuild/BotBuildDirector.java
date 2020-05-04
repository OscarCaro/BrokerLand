package model.players.botBuild;

import controller.Difficulty;
import model.locations.WorldMap;
import model.players.Bot;
import model.players.chooseActionStrategies.ChooseActionStrategy;
import model.players.chooseActionStrategies.FastMovingStrategy;
import model.players.chooseActionStrategies.SedentatyStrategy;
import model.players.marketstrategies.*;
import model.players.socialStrategies.FlirtyStrategy;
import model.players.socialStrategies.RoughStrategy;
import model.players.socialStrategies.ShyStrategy;
import model.players.socialStrategies.SocialStrategy;
import model.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BotBuildDirector {

    public static final Difficulty DEFAULT_DIFFICULTY = Difficulty.NORMAL;
    
    private BotBuilder builder;
    
    public BotBuildDirector(BotBuilder builder) {
    	this.builder = builder;
    }
	
	public List<Bot> build(int botsNum, Difficulty diff){
		List<Bot> list = new ArrayList<>();
		if (botsNum == -1) {
			botsNum = diff.getDifficultyBotsNum();
		}
		builder.setInitLoc(WorldMap.HOMEIDX).setMoney(1000);		// Common for all
		
        for (int i = 0; i < botsNum * diff.getAggressiveRatio(); i++) {
        	builder.setName(Utils.generateName(), Utils.generateSurname())
        	.setMarketStrategy(new aggressiveStrategy())
        	.setSocialStrategy(pickSocStr())
        	.setChooseActionStrategy(pickActStr());
            list.add(builder.build());
        }
        
        for (int i = 0; i < botsNum * diff.getDumbassRatio(); i++) {
        	builder.setName(Utils.generateName(), Utils.generateSurname())
        	.setMarketStrategy(new dumbassStrategy())
        	.setSocialStrategy(pickSocStr())
        	.setChooseActionStrategy(pickActStr());
            list.add(builder.build());
        }
        
        for (int i = 0; i < botsNum * diff.getKnowledgeableRatio(); i++) {
        	builder.setName(Utils.generateName(), Utils.generateSurname())
        	.setMarketStrategy(new knowledgeableStrategy())
        	.setSocialStrategy(pickSocStr())
        	.setChooseActionStrategy(pickActStr());
            list.add(builder.build());
        }
        
        for (int i = 0; i < botsNum * diff.getGreedyRatio(); i++) {
        	builder.setName(Utils.generateName(), Utils.generateSurname())
        	.setMarketStrategy(new greedyStrategy())
        	.setSocialStrategy(pickSocStr())
        	.setChooseActionStrategy(pickActStr());
            list.add(builder.build());
        }
        
        for (int i = 0; i < botsNum * diff.getRandomRatio(); i++) {
        	builder.setName(Utils.generateName(), Utils.generateSurname())
        	.setMarketStrategy(new randomStrategy())
        	.setSocialStrategy(pickSocStr())
        	.setChooseActionStrategy(pickActStr());
            list.add(builder.build());
        }
        
        while (list.size() < botsNum) {
        	builder.setName(Utils.generateName(), Utils.generateSurname())
        	.setMarketStrategy(new randomStrategy())
        	.setSocialStrategy(pickSocStr())
        	.setChooseActionStrategy(pickActStr());
            list.add(builder.build());
        }
		while (list.size() > botsNum) {
			list.remove(Utils.randomNum(list.size()));
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
	
	private ChooseActionStrategy pickActStr() {
		switch (Utils.randomNum(2)) {
			case 0: return new SedentatyStrategy();
			default: return new FastMovingStrategy();
		}
	}
}
