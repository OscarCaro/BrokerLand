package controller;

public enum Difficulty {
	
    EASY(0.4, 0.1, 0.1, 0.1, 0.3),
    NORMAL(0.3, 0.2, 0.2, 0.1, 0.2),
    DIFFICULT(0.1, 0.2, 0.3, 0.2, 0.2),
    WORLDTRADECENTER(0, 0.2, 0.3, 0.4, 0.1);
	
    private double dumbassRatio;
    private double aggressiveRatio;
    private double greedyRatio;
    private double knowledgeableRatio;
    private double randomRatio;

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
}

