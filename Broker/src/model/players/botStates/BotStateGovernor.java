package model.players.botStates;

import model.players.Bot;
import model.utils.Utils;

public class BotStateGovernor {
	
    private BotState currState;
    private double adaptability;
    
    // This are just "algorithms", enough to have 1 instance for all Bots
    private static BotState hunger = new HungerProblemsState();
    private static BotState mental = new MentalProblemsState();
    private static BotState defeated = new DefeatedState();
    private static BotState hardWork = new HardWorkingState();
    private static BotState initial = new InitialState();
    private static BotState lazy = new LazyState();
    private static BotState luxurious = new LuxuriousState();
    private static BotState normal = new NormalState();

    public BotStateGovernor(double adaptability) {
        this.adaptability = adaptability;
        currState = initial;        
    }

    public void update(Bot b) {
    	BotState nextState;
    	int aux = Utils.randomNum(100);
        
    	// The higher the adaptability, the more responsive to changes the bot is
        if (Utils.randomNum(100) < 100 * this.adaptability) {
            if (b.getHunger() < 50) {
            	nextState = hunger;
            } else if (b.getMental() < 20) {
            	nextState = mental;
            } else if (b.getMoney() > 1500 && Utils.randomNum(10) < 4) {
            	nextState = luxurious;
            } else if (aux < 50) {
            	nextState = hardWork;
            } else if (aux < 58) {
            	nextState = defeated;
            } else if (aux < 75) {
            	nextState = lazy;
            } else {
            	nextState = normal;
            }  
            
            if (nextState != currState) {
            	// There's a change in state, call update to notify
            	currState = nextState;
                this.currState.update(b);
            }
        }
    }
}
