package controller.Difficulties;

public class CustomDifficulty implements Difficulty{
    private double dumbassRatio;
    private double aggressiveRatio;
    private double greedyRatio;
    private double knowledgeableRatio;
    private double randomRatio;
    private double adaptability;
    private int marketMinAssets;
    private int marketStartingAssets;
    private int difficultyBotsNum;

    public CustomDifficulty(
            double dumbassRatio,
            double aggressiveRatio,
            double greedyRatio,
            double knowledgeableRatio,
            double randomRatio,
            double adaptability,
            int marketMinAssets,
            int marketStartingAssets,
            int difficultyBotsNum
    ) {
        this.dumbassRatio = dumbassRatio;
        this.aggressiveRatio = aggressiveRatio;
        this.greedyRatio = greedyRatio;
        this.knowledgeableRatio = knowledgeableRatio;
        this.randomRatio = randomRatio;
        this.adaptability = adaptability;
        this.marketMinAssets = marketMinAssets;
        this.marketStartingAssets = marketStartingAssets;
        this.difficultyBotsNum = difficultyBotsNum;
    }

    public double getDumbassRatio() {
        return dumbassRatio;
    }

    public double getAggressiveRatio() {
        return aggressiveRatio;
    }

    public double getGreedyRatio() {
        return greedyRatio;
    }

    public double getKnowledgeableRatio() {
        return knowledgeableRatio;
    }

    public double getRandomRatio() {
        return randomRatio;
    }

    public int getDifficultyBotsNum() {
        return difficultyBotsNum;
    }

    public double getAdaptabilityRatio() {
        return adaptability;
    }

    public int getMarketStartingAssets() {
        return marketStartingAssets;
    }

    public int getMarketMinAssets() {
        return marketMinAssets;
    }
}
