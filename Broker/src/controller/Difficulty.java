package controller;

public enum Difficulty {
    EASY(0.4, 0.1, 0.1, 0.1, 0.3),
    NORMAL(0.3, 0.2, 0.2, 0.1, 0.2),
    DIFFICULT(0.1, 0.2, 0.3, 0.2, 0.2),
    WORLDTRADECENTER(0, 0.2, 0.3, 0.4, 0.1);
    double dumbassRatio;
    double aggressiveRatio;
    double greedyRatio;
    double knowledgeableRatio;
    double randomRatio;

    private Difficulty(
            double dumbassRatio,
            double aggressiveRatio,
            double greedyRatio,
            double knowledgeableRatio,
            double randomRatio
    ){
        this.dumbassRatio = dumbassRatio;
        this.aggressiveRatio = aggressiveRatio;
        this.greedyRatio = greedyRatio;
        this.knowledgeableRatio = knowledgeableRatio;
        this.randomRatio = randomRatio;
    }
    public static Difficulty parse(String inputString) {
        for (Difficulty level : Difficulty.values())
            if (level.name().equalsIgnoreCase(inputString))
                return level;
        return null;
    }
}

