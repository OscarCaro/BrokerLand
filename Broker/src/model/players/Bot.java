package model.players;


import controller.Game;
import model.actions.Action;
import model.events.BotUpdateEvent;
import model.events.Event;
import model.events.EventHandler;
import model.life.Time;
import model.locations.WorldMap;
import model.players.marketstrategies.MarketStrategy;
import model.players.marketstrategies.dumbassStrategy;
import model.players.marketstrategies.randomStrategy;
import model.players.socialStrategies.SocialStrategy;
import model.trading.Banker;
import model.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Bot extends Player {

    private MarketStrategy marketStrategy;
    private SocialStrategy socialStrategy;
    private boolean hasActionScheduled;

    public Bot(String name, String surname, int locIdx, int money, 
    		MarketStrategy marketStrategy, SocialStrategy socialStrategy) {
        super(name, surname, locIdx, money);
        this.marketStrategy = marketStrategy;
        this.socialStrategy = socialStrategy;
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
    public void update() {
        if (!hasActionScheduled) {        	
        	/* To be able to manage the probability in which a bot changes location.
        	 * If not dealt in this way, the prob would be so high that bots spend the day moving
        	 * and doing nothing.
        	 * TODO: Add this feature as part of bot's personality strategy
        	 */
        	List<Action> actions;
        	if (Utils.randomNum(100) > 30) {
        		actions = new ArrayList<>(currLoc.getActions());
        	}
        	else {
        		actions = new ArrayList<>(currLoc.getMoveActions());
        	}
            
            Action action = actions.get(Utils.randomNum(actions.size()));
            Time triggerTime = Game.getTimeClone();
            triggerTime.addTime(action.getTime());

            Event event = new BotUpdateEvent(triggerTime, action, this);
            EventHandler.getInstance().addEvent(event);
        }
    }
    
    @Override
    public void reactToGreeting(Player other, String message) {
    	this.socialStrategy.reactToGreeting(this, other);    	
    }
    
    @Override
    public String getMessageToSay() {
    	return this.socialStrategy.getMessageToSay(); 
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
                aux = this.getName() + " ended his miserable existence.";
            } else {
                aux = this.getName() + " went insane and deeply depressed, he quit trading and entered therapy.";
            }
        } else if (this.hunger.starved()) {
            int rStarvation = Utils.randomNum(100);
            if (rStarvation < 10) {
                aux = this.getName() + " was hospitalized too late,\nhis body rejected food and he died of famine.";
            } else if (rStarvation < 40) {
                aux = this.getName() + " was so focused on work he didn't eat for a while.\nHe fainted and fell, and is now hospitalized with a severe head trauma.";
            } else {
                aux = this.getName() + " was very focused on work and forgot to eat.\nHe was hospitalized, for his own well-being has been dictated to give up trading.";
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
            System.out.println(getName() + " looked to his portfolio and found it empty.");
        }
    }

    public void setHasActionScheduled(boolean b) {
        this.hasActionScheduled = b;
    }

}
