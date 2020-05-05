package model.trading;

import controller.Game;
import model.life.Time;
import model.players.Player;
import model.trading.assetStates.AssetState;
import model.trading.assetStates.BankruptState;
import model.trading.assetStates.IndustryBoomState;
import model.trading.assetStates.IndustryCrashState;
import model.trading.assetStates.NormalState;
import model.utils.Pair;
import model.utils.SortedArrayList;
import model.utils.Utils;

public class Asset {

    private static final int maxSquaringSize = 18;
    private static final int BANKRUPTCYTURNS = 3;
    
    private AssetState state;
    public int price;
    public String name;
    public int sharesOwned; //number of shares that are sold and are brokers properties as of now
    public int curve10;
    private SortedArrayList<Pair<Time, Integer>> record;
    private int bankruptcyIndex;
    
    public Asset() {
    	state = new NormalState();
        price = priceGen();
        name = Utils.assetNameGen();
        sharesOwned = 0;
        curve10 = 0;
        record = new SortedArrayList<>((o1, o2) -> {
            if (o1.getKey().day > o2.getKey().day) return 1;
            else return 0;
        });
        bankruptcyIndex = 0;
    }

    public boolean buy(Player player, int quantity) {
        int fullp = price * quantity;
        if (player.getMoney() >= fullp) {
            player.modifyMoney(-1 * fullp);
            sharesOwned += quantity;
            record.add(new Pair<>(Game.getTimeClone(), quantity));
            updateCurve10();
            decreaseBIndex();
            decreaseBIndex();
            return true;
        }
        return false;
    }

    public void decreaseBIndex() {
        bankruptcyIndex = Math.max(0, bankruptcyIndex - 1);
    }
    
    public void incrementBIndex() {
    	this.bankruptcyIndex++;
    }

    private void updateCurve10() {
        while (record.get(0).getKey().day - Game.t.day > 10) {
            record.remove(0);
        }
        for (Pair<Time, Integer> p : record) {
            curve10 += p.getValue();
        }
    }

    public boolean sell(Player player, int quantity) {
        player.modifyMoney(price * quantity);
        sharesOwned -= quantity;
        record.add(new Pair<>(Game.getTimeClone(), -quantity));
        updateCurve10();
        return true;
    }

    public void refreshAsset() {
    	this.price = state.getNewPrice(this);
    	this.state = state.getNextState(this);
    	
    	if (sharesOwned == 0 || record.isEmpty() ||  
        	sharesOwned < (int) (0.01 * (double) Game.getTimeClone().day) ) {
            bankruptcyIndex++;
        }

        if (bankruptcyIndex > BANKRUPTCYTURNS || this.price == 0) {
            state = new BankruptState();
        }
    }

    public String toString() {
        return "Name: " + this.name + Utils.spaces(maxSquaringSize - this.name.length()) + " Price " + "$" + this.price + ".";
    }

    private int priceGen() {
        return Utils.randomNum(10 + (Game.t.day * 10)) + 20;
    }

    public void setBankrupt() {
        state = new BankruptState();
    }

    public int getBankruptcyIndex() {
        return bankruptcyIndex;
    }
    
    public int getCurve10() {
    	return this.curve10;
    }

    public int getPrice() {
        return this.price;
    }

    public boolean isIndustryCrash() {
        return state instanceof IndustryCrashState;
    }

    public boolean isIndustryBoom() {
        return state instanceof IndustryBoomState;
    }
    
    public boolean isBankrupt() {
        return state instanceof BankruptState;
    }
}