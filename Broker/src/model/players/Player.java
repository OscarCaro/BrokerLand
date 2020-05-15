package model.players;

import model.life.Hunger;
import model.life.MentalHealth;
import model.life.Time;
import model.locations.Location;
import model.locations.LocationChanger;
import model.locations.WorldMap;
import model.trading.Asset;
import model.trading.Banker;
import model.trading.Loan;
import model.trading.Market;
import model.utils.Pair;
import model.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected Location currLoc;
    protected LocationChanger map;
    protected Market globalMarket;
    protected MentalHealth mentalH;
    protected Hunger hunger;
    protected int money;
    protected Time ownTime;
    protected String name;
    protected String surname;
    protected List<Pair<Asset, Integer>> portfolio;
    protected Loan loan;
    protected Sex sex;

    public Player(String name, String surname, int locIdx, int money) {
        this.map = WorldMap.getInstance();
        map.startIn(this, locIdx);
        this.name = name;
        this.surname = surname;
        this.money = money;
        this.globalMarket = Market.getInstance();
        this.mentalH = new MentalHealth(100);
        this.hunger = new Hunger(100);
        this.ownTime = new Time();
        this.portfolio = new ArrayList<>();
        this.loan = null;
    }

    public abstract void update();

    public abstract void buy();

    public abstract void sell();

    public abstract void reactToGreeting(Player other, String message);

    public abstract String getMessageToSay();

    public abstract Player choosePlayerToGreet(List<Player> players);

    protected void moveTo(int locIdx) {
        map.moveTo(this, locIdx);
    }

    public boolean canContinue(boolean isUser) {
        if (this.getMoney() < 0 && !this.getPortfolio().isEmpty()) {
            this.sellAssetsDebt();
        }
        if (this.getMoney() < 0 && !this.hasLoan()) {
            Banker.getInstance().savingLoan(this.getMoney(), this, isUser);
        }
        return !this.mentalH.insane() && this.getMoney() >= 0 && !this.hunger.starved();
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

    public void modifyHunger(int amount) {
        this.hunger.add(amount);
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

    public Time getTime() {
        return this.ownTime;
    }

    public String getName() {
        return this.name + " " + this.surname;
    }

    public List<Pair<Asset, Integer>> getPortfolio() {
        return portfolio;
    }

    public abstract boolean takeLoan();

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
            this.addPortfolio(globalMarket.assets.get(marketIndex), qtty);
            return true;
        }
    }

    private void addPortfolio(Asset a, Integer qtty) {
        boolean add = false;
        for (Pair<Asset, Integer> ps : portfolio) {
            if (ps.getKey().equals(a)) {
                add = true;
                int aux = ps.getValue();
                portfolio.remove(ps);
                portfolio.add(new Pair<>(a, aux + qtty));
                break;
            }
        }
        if (!add) {
            portfolio.add(new Pair<>(a, qtty));
        }
    }


    public abstract boolean payBackLoan();

    public boolean hasLoan() {
        return loan != null;
    }

    protected void payLoanAmount(int amount) {
        loan.pay(amount);
        this.modifyMoney(-amount);
    }

    public void payInstallment(boolean isUser, int installment) {
        if (this.hasLoan()) {
            if (isUser) {
                System.out.println("You paid $ " + loan.getInstallment() + " off of your loan debt.");
            } else {
                System.out.println(this.getName() + " is being crushed by debt.");
            }
            this.payLoanAmount(installment);
        }
    }

    public void takeSavingLoan(Loan loan) {
        this.loan = loan;
    }

    public LocationChanger getLocationChanger() {
        return this.map;
    }

    public void flushAsset(Asset a, boolean isUser) {
        for (Pair<Asset, Integer> p : portfolio) {
            if (p.getKey().equals(a)) {
                if (isUser) {
                    Utils.minusWall();
                    System.out.println("You got rid of " + p.getValue() + " assets of " + p.getKey().name + " since it went bankrupt.");
                    Utils.minusWall();
                } else {
                    System.out.println(this.getName() + " was affected by the bankruptcy of " + p.getKey().name + ".");
                }
                this.modifyMoney((p.getKey().price * p.getValue()) / 2);
                this.modifyHealth(-10);
                portfolio.remove(p);
                break;
            }
        }
    }

    public int getHunger() {
        return this.hunger.getHungerIndex();
    }

    public int getMental() {
        return this.mentalH.getMentalIndex();
    }

    public Sex getSex() {
        return sex;
    }

    public abstract void sellAssetsDebt();
}


