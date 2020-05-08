package model.players.botBuild;

import controller.Difficulties.Difficulty;
import controller.Difficulties.PremadeDifficulty;
import model.locations.WorldMap;
import model.players.Bot;
import model.players.marketstrategies.*;
import model.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BotBuildDirector {

    public static final PremadeDifficulty DEFAULT_PREMADE_DIFFICULTY = PremadeDifficulty.NORMAL;

    private BotBuilder builder;

    public BotBuildDirector(BotBuilder builder) {
        this.builder = builder;
    }

    public List<Bot> build(int botsNum, Difficulty diff) {
        List<Bot> list = new ArrayList<>();
        if (botsNum == -1) {
            botsNum = diff.getDifficultyBotsNum();
        }
        builder.setInitLoc(WorldMap.HOMEIDX).setMoney(1000);        // Common for all

        for (int i = 0; i < botsNum * diff.getAggressiveRatio(); i++) {
            builder.generateSex()
                    .generateName()
                    .setMarketStrategy(new aggressiveStrategy())
                    .setStateGovernorMutability(diff.getAdaptabilityRatio());
            list.add(builder.build());
        }

        for (int i = 0; i < botsNum * diff.getDumbassRatio(); i++) {
            builder.generateSex()
                    .generateName()
                    .setMarketStrategy(new dumbassStrategy())
                    .setStateGovernorMutability(diff.getAdaptabilityRatio());
            list.add(builder.build());
        }

        for (int i = 0; i < botsNum * diff.getKnowledgeableRatio(); i++) {
            builder.generateSex()
                    .generateName()
                    .setMarketStrategy(new knowledgeableStrategy())
                    .setStateGovernorMutability(diff.getAdaptabilityRatio());
            list.add(builder.build());
        }

        for (int i = 0; i < botsNum * diff.getGreedyRatio(); i++) {
            builder.generateSex()
					.generateName()
                    .setMarketStrategy(new greedyStrategy())
                    .setStateGovernorMutability(diff.getAdaptabilityRatio());
            list.add(builder.build());
        }

        for (int i = 0; i < botsNum * diff.getRandomRatio(); i++) {
            builder.generateSex()
					.generateName()
                    .setMarketStrategy(new randomStrategy())
                    .setStateGovernorMutability(diff.getAdaptabilityRatio());
            list.add(builder.build());
        }

        while (list.size() < botsNum) {
            builder.generateSex()
					.generateName()
                    .setMarketStrategy(new randomStrategy())
                    .setStateGovernorMutability(diff.getAdaptabilityRatio());
            list.add(builder.build());
        }

        while (list.size() > botsNum) {
            list.remove(Utils.randomNum(list.size()));
        }

        return list;
    }


}
