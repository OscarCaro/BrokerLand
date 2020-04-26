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
import model.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Bot extends Player {
	
    private MarketStrategy strategy;
    private boolean hasActionScheduled;

    public Bot() {
        super(Utils.generateName(), Utils.generateSurname(), WorldMap.HOMEIDX, 1000);
        this.strategy = strategyDefine();
        hasActionScheduled = false;
    }

    public Bot(MarketStrategy strat){
        super(Utils.generateName(), Utils.generateSurname(), WorldMap.HOMEIDX, 1000);
        this.strategy = strat;
    }

    private MarketStrategy strategyDefine() {
        if (Utils.randomNum(10)>5) //sketchy still
            return new randomStrategy();
        else
            return new dumbassStrategy();
    }

    @Override
    public void update() {
        if (!hasActionScheduled) {
			List<Action> actions = new ArrayList<>(currLoc.getActions()); // Call returns an unmodif. list
			Action action = actions.get(Utils.randomNum(actions.size()));
			Time triggerTime = Game.getTimeClone();
			triggerTime.addTime(action.getTime());
			
			int newLocIdx = Utils.randomNum(map.getNumOfLocs());
			
			Event event = new BotUpdateEvent(triggerTime, action, currLoc, newLocIdx, map, this);
			EventHandler.getInstance().addEvent(event);	       
		} 
    }

    public String endMessage() {
        String aux = null;
        if (mentalH.insane()) {
            int rSuicide = Utils.randomNum(100);
            if (rSuicide < 10) {
                aux = this.getName() + " ended his miserable existence.";
            } else {
                aux = this.getName() + " went insane and deeply depressed, he quit trading and entered therapy.";
            }
        }
        else if(this.hunger.starved()){
            int rStarvation = Utils.randomNum(100);
            if (rStarvation < 10) {
                aux = this.getName() + " was hospitalized too late,\n his body rejected food and he died of famine.";
            }
            else if (rStarvation < 40){
                aux = this.getName() + " was so focused on work he didn't eat for a while.\n He fainted and fell, and is now hospitalized with a severe head trauma.";
            }
            else{
                aux = this.getName() + " was very focused on work and forgot to eat.\n He has been hospitalized, and for his own well-being has been recommended to give up trading.";
            }
        }
        else {
            aux = this.getName() + " went bankrupt and fled.";
        }
        return aux;
    }

    @Override
    public void buy() {
        this.strategy.buyAsset(this);
    }

    @Override
    public void sell() {
        if (!this.portfolio.isEmpty()) {
            this.strategy.sellAsset(this);
        } else {
            System.out.println(getName() + " looked to his portfolio and found it empty.");
        }
    }

    public void setHasActionScheduled(boolean b) {
    	this.hasActionScheduled = b;
    }

}
