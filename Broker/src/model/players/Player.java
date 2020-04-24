package model.players;

import model.life.MentalHealth;
import model.life.Time;
import model.locations.Location;
import model.locations.LocationChanger;
import model.locations.WorldMap;
import model.trading.Asset;
import model.trading.Market;
import model.utils.Pair;
import model.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Player {

    protected Location currLoc;
    protected LocationChanger map;
    protected Market globalMarket;
    protected MentalHealth mentalH;
    protected int money;
    protected Time ownTime;        //To be used when we add more players (MODS)
    protected String name;
    protected String surname;
    protected List<Pair<Asset, Integer>> portfolio;

    public Player(String name, String surname, int locIdx, int money) {
        this.map = WorldMap.getInstance();
        map.startIn(this, locIdx);
        this.name = name;
        this.surname = surname;
        this.money = money;
        this.globalMarket = Market.getInstance();
        this.mentalH = new MentalHealth(100);
        this.ownTime = new Time();
        this.portfolio = new ArrayList<>();
    }

    public abstract void update();

    public abstract void buy();

    public abstract void sell();

    protected void moveTo(int locIdx) {
        map.moveTo(this, locIdx);
    }

    public boolean canContinue(boolean isUser) {
        if (isUser) {
            return !this.mentalH.insane() && this.getMoney() > 0;
        } else {
            boolean aux = false;
            for (Asset a : this.globalMarket.assets) {
                if (this.money > a.price) {
                    aux = true;
                }
            }
            return aux && !this.mentalH.insane();
        }
    }

    public Location getCurrLoc() {
        return this.currLoc;
    }

    public void setCurrLoc(Location newLoc) {
        this.currLoc = newLoc;
    }

    public void modifyHealth(int amount) {
        this.mentalH.add(amount);
    }

    public int getMoney() {
        return this.money;
    }

    public void modifyMoney(int amount) {
        this.money += amount;
    }

    public void addTime(int minutes) {
        this.ownTime.addTime(minutes);
    }

    public Market getGlobalMarket() {
        return this.globalMarket;
    }

    public String getName() {
        return this.name + " " + this.surname;
    }

    public List<Pair<Asset, Integer>> getPortfolio() {
        return portfolio;
    }

    public boolean playerSellAsset(int portfolioIndex, int qtty) { //NEEDS TO BE ACCESSED FROM BOT STRATEGIES
        if (globalMarket.sell(this, globalMarket.marketIndex(portfolio.get(portfolioIndex).getKey()), qtty)) {
            Pair<Asset, Integer> p = new Pair<>(portfolio.get(portfolioIndex).getKey(), portfolio.get(portfolioIndex).getValue() - qtty);
            portfolio.remove(portfolioIndex);
            if (p.getValue() > 0) {
                portfolio.add(p);
            } //If I did not sell all of them I must keep the pair with the new value of qtty
            return true;
        }
        return false;
    }

    public boolean playerBuyAsset(int marketIndex, int qtty) {
        if (!globalMarket.buy(this, marketIndex, qtty)) {
            return false;
        } else {
            portfolio.add(new Pair<>(globalMarket.assets.get(marketIndex), qtty));
            return true;
        }
    }

    public void flushAssets(boolean isUser){
        Iterator<Pair<Asset, Integer>> iter = portfolio.iterator();
        while (iter.hasNext()) {
            Pair<Asset, Integer> p = iter.next();
            if (p.getKey().isBankrupt()) {

                if (isUser){
                    Utils.minusWall();
                    System.out.println("You got rid of " + p.getValue() + " assets of " + p.getKey().name + " since it went bankrupt.");
                    Utils.minusWall();
                }
                else{
                    System.out.println(this.name + " was affected by the bankruptcy of " + p.getKey().name + "!");
                }
                this.modifyMoney( p.getKey().price*p.getValue());
                iter.remove();
            }
        }
    }
}