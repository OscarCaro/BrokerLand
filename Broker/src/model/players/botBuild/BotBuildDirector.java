package model.players.botBuild;

import controller.Difficulties.Difficulty;
import controller.Difficulties.PremadeDifficulty;
import model.locations.WorldMap;
import model.players.Bot;
import model.players.botBuild.factories.AggresiveBotFactory;
import model.players.botBuild.factories.BotFactory;
import model.players.botBuild.factories.DumbassBotFactory;
import model.players.botBuild.factories.GreedyBotFactory;
import model.players.botBuild.factories.KnowledgeableBotFactory;
import model.players.botBuild.factories.RandomBotFactory;
import model.players.marketstrategies.*;
import model.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BotBuildDirector {

    public static final PremadeDifficulty DEFAULT_PREMADE_DIFFICULTY = PremadeDifficulty.NORMAL;
    
    private BotFactory aggrFactory = new AggresiveBotFactory();
    private BotFactory dumbFactory = new DumbassBotFactory();
    private BotFactory greedyFactory = new GreedyBotFactory();
    private BotFactory knowledgeableFactory = new KnowledgeableBotFactory();
    private BotFactory randomFactory = new RandomBotFactory();

    public List<Bot> build(int botsNum, Difficulty diff) {
        List<Bot> list = new ArrayList<>();
        if (botsNum == -1) {
            botsNum = diff.getDifficultyBotsNum();
        }
        
        for (int i = 0; i < botsNum * diff.getAggressiveRatio(); i++) {            
            list.add(aggrFactory.generateBot(diff));
        }

        for (int i = 0; i < botsNum * diff.getDumbassRatio(); i++) {
            list.add(dumbFactory.generateBot(diff));
        }

        for (int i = 0; i < botsNum * diff.getKnowledgeableRatio(); i++) {
            list.add(knowledgeableFactory.generateBot(diff));
        }

        for (int i = 0; i < botsNum * diff.getGreedyRatio(); i++) {
            list.add(greedyFactory.generateBot(diff));
        }

        for (int i = 0; i < botsNum * diff.getRandomRatio(); i++) {
            list.add(randomFactory.generateBot(diff));
        }

        while (list.size() < botsNum) {
            list.add(randomFactory.generateBot(diff));
        }

        while (list.size() > botsNum) {
            list.remove(Utils.randomNum(list.size()));
        }

        return list;
    }


}
