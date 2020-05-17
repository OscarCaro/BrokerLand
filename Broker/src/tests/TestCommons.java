package tests;

import controller.Difficulties.PremadeDifficulty;
import controller.Game;
import junit.framework.TestCase;
import model.players.Bot;
import model.players.Sex;
import model.players.marketstrategies.randomStrategy;

class TestCommons extends TestCase {

    /**
     * This function inits the Game in Easy Mode which also inits the Market according to Easy difficulty
     */
    void initGameAndMarket() {
        new Game(PremadeDifficulty.EASY);
    }

    Bot botDummy() {
        return new Bot("Dummy", "McDumdum", 0, 1000, new randomStrategy(), 0.8, Sex.MALE);
    }
}
