package model.players;


import controller.Game;
import model.actions.Action;
import model.locations.WorldMap;
import model.players.marketstrategies.MarketStrategy;
import model.players.marketstrategies.dumbassStrategy;
import model.players.marketstrategies.randomStrategy;
import model.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bot extends Player {
    private MarketStrategy strategy;

    public Bot() {
        super(Utils.generateName(), Utils.generateSurname(), WorldMap.HOMEIDX, 1000);
        this.strategy = strategyDefine();
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
        Utils.minusWall();
        askActions();
        //System.out.println(ownTime);
        askNewLocation();
    }

    private void askActions() {

        List<Action> actions = new ArrayList<>(currLoc.getActions());	// Call returns an unmodif. list

    	int availableTime = Game.t.minus(ownTime);
    	
    	// Remove actions that take longer than available time
    	Iterator<Action> i = actions.iterator();
    	while (i.hasNext()) {
    		Action a = i.next();
    		if (a.getTime() > availableTime) {
    			i.remove();
    		}
    	}

    	// Choose an action randomly (maybe later using a strategy)
    	if (!actions.isEmpty()) {
    		int idx = Utils.randomNum(actions.size());
    		actions.get(idx).perform(this, false);
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
        } else {
            aux = this.getName() + " went bankrupt and fled.";
        }
        return aux;
    }

    private void askNewLocation() {
        if (Utils.randomNum(101) > 50) {
            String auxn = currLoc.name;
            map.moveTo(this, Utils.randomNum(map.getNumOfLocs()));
            if (auxn != currLoc.name)
                System.out.println(getName() + " went to " + currLoc + ".");        // TODO: change janky implementation
        }
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


}
