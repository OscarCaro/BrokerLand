package model.players;


import model.locations.WorldMap;
import model.players.marketstrategies.MarketStrategy;
import model.players.marketstrategies.randomStrategy;
import model.utils.Utils;

public class Bot extends Player {
    private MarketStrategy strategy;

    public Bot() {
        super(Utils.generateName(), Utils.generateSurname(), WorldMap.HOMEIDX, 1000);
        this.strategy = strategyDefine(/*difficulty of level to create more aggresive personalities(?)*/);
    }

    private MarketStrategy strategyDefine() {
        return new randomStrategy(); //TODO
    }

    @Override
    public void update() {
        Utils.minusWall();
        askActions();
        askNewLocation();
    }

    private void askActions() {
        currLoc.performAction(this, Utils.randomNum(currLoc.getNumOfActions()), false);
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
