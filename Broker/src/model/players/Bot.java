package model.players;


import controller.Game;
import model.actions.Action;
import model.events.BotUpdateEvent;
import model.events.Event;
import model.events.EventHandler;
import model.life.Time;
import model.players.botStates.BotStateGovernor;
import model.players.marketstrategies.MarketStrategy;
import model.trading.Banker;
import model.utils.Utils;

import java.util.List;

public class Bot extends Player {

    private MarketStrategy marketStrategy;

    private boolean hasActionScheduled;
    private BotStateGovernor mind;

    public Bot(String name, String surname, int locIdx, int money, MarketStrategy marketStrategy, double adaptability, Sex sex) {
        super(name, surname, locIdx, money);
        this.marketStrategy = marketStrategy;
        this.mind = new BotStateGovernor(adaptability);
        this.sex = sex;
    }

    @Override
    public boolean takeLoan() {
        if (this.loan == null) {
            this.loan = Banker.getInstance().calculateLoan(this);
            Banker.getInstance().giveLoan(loan, this, false);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean payBackLoan() {
        if (this.hasLoan()) {
            if (this.loan.getAmountRemaining() <= this.getMoney()) {
                this.payLoanAmount(this.loan.getAmountRemaining());
                loan = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public void sellAssetsDebt() {
        this.marketStrategy.sellAssetDebt(this);
    }

    @Override
    public void update() {
        if (!hasActionScheduled) {
            Action action = mind.getState().chooseAction(currLoc.getActions(), currLoc.getMoveActions());
            Time triggerTime = Game.getTimeClone();
            triggerTime.addTime(action.getTime());
            Event event = new BotUpdateEvent(triggerTime, action, this);
            EventHandler.getInstance().addEvent(event);
        }
    }

    @Override
    public void reactToGreeting(Player other, String message) {
        mind.getState().reactToGreeting(this, other);
    }

    @Override
    public String getMessageToSay() {
        return mind.getState().getMessageToSay();
    }

    @Override
    public Player choosePlayerToGreet(List<Player> players) {
        return players.get(Utils.randomNum(players.size()));
    }

    public String endMessage() {
        String aux;
        if (mentalH.insane()) {
            int rSuicide = Utils.randomNum(100);
            if (rSuicide < 10) {
                aux = this.getName() + " ended " + Sex.objectPronoun(this.getSex()) + " miserable existence.";
            } else {
                aux = this.getName() + " went insane and deeply depressed, " + Sex.subjectPronoun(this.getSex(), false) + " quit trading and entered therapy.";
            }
        } else if (this.hunger.starved()) {
            int rStarvation = Utils.randomNum(100);
            if (rStarvation < 10) {
                aux = this.getName() + " was hospitalized too late,\n" + Sex.objectPronoun(this.getSex()) + " body rejected food and " + Sex.subjectPronoun(this.getSex(), false) + " died of famine.";
            } else if (rStarvation < 40) {
                aux = this.getName() + " was so focused on work he didn't eat for a while.\n" + Sex.subjectPronoun(this.getSex(), true) + " fainted and fell, and is now hospitalized with a severe head trauma.";
            } else {
                aux = this.getName() + " was very focused on work and forgot to eat.\n" + Sex.subjectPronoun(this.getSex(), true) + " was hospitalized, for " + Sex.objectPronoun(this.getSex()) + " own well-being has been dictated to give up trading.";
            }
        } else {
            aux = this.getName() + " went bankrupt and fled.";
        }
        return aux;
    }

    @Override
    public void buy() {
        this.marketStrategy.buyAsset(this);
    }

    @Override
    public void sell() {
        if (!this.portfolio.isEmpty()) {
            this.marketStrategy.sellAsset(this);
        } else {
            System.out.println(getName() + " looked to " + Sex.objectPronoun(this.getSex()) + " portfolio and found it empty.");
        }
    }

    public void sellPortfolioOnDeath() { //this takes care of the will of the bots as well as it keeps the assets owned index correct
        if (!portfolio.isEmpty()) {
            this.playerSellAsset(0, portfolio.get(0).getValue());
            this.sellPortfolioOnDeath();
        }
    }

    public void setHasActionScheduled(boolean b) {
        this.hasActionScheduled = b;
    }

    public void updateMind() {
        this.mind.update(this);
    }
}
