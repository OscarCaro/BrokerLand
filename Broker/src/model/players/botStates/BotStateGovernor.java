package model.players.botStates;

import model.players.Bot;
import model.utils.Utils;

public class BotStateGovernor {
    private BotState state;
    private double adaptability;

    public BotStateGovernor(double adaptability) {
        this.adaptability = adaptability;
    }

    public void update(Bot b) {
        BotState stateIn = state;
        if (state == null){
            state = new InitialState();
        }
        else {
            if (Utils.randomNum(100) < 100 * this.adaptability) {
                if (b.getHunger() < 50) {
                    state = new HungerProblemsState();
                } else if (b.getMental() < 20) {
                    state = new MentalProblemsState();
                }
                else {

                    int aux = Utils.randomNum(100);
                    if (aux < 50) {
                        state = new HardWorkingState();
                    } else if (aux < 58) {
                        state = new DefeatedState();
                    } else if (aux < 75) {
                        state = new LazyState();
                    } else {
                        state = new NormalState();
                    }
                    if (b.getMoney() > 1500 && Utils.randomNum(10) < 4) {
                        state = new LuxuriousState();
                    }
                }
            }
        }
        if (stateIn == null || stateIn != state) {
            this.state.update(b);
        }
    }
}
