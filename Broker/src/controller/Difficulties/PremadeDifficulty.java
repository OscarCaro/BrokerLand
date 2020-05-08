package controller.Difficulties;

public enum PremadeDifficulty implements Difficulty{

    EASY(0.4, 0.1, 0.1, 0.1, 0.3, 0.4, 3, 5, 20),
    NORMAL(0.3, 0.2, 0.2, 0.1, 0.2, 0.5, 4, 6, 30),
    DIFFICULT(0.1, 0.2, 0.3, 0.2, 0.2, 0.7, 5, 7, 40),
    WORLDTRADECENTER(0, 0.2, 0.3, 0.4, 0.1, 0.8, 5, 8, 50);

    private double dumbassRatio;
    private double aggressiveRatio;
    private double greedyRatio;
    private double knowledgeableRatio;
    private double randomRatio;
    private double adaptability;
    private int marketMinAssets;
    private int marketStartingAssets;
    private int difficultyBotsNum;

    private PremadeDifficulty(
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



    public static PremadeDifficulty parse(String inputString) {
        for (PremadeDifficulty level : PremadeDifficulty.values())
            if (level.name().equalsIgnoreCase(inputString))
                return level;
        return null;
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
