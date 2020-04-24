package model.trading;

import controller.Game;
import model.life.Time;
import model.players.Player;
import model.utils.Pair;
import model.utils.SortedArrayList;
import model.utils.Utils;

public class Asset {

    private static final int maxSquaringSize = 18;
    private static final int BANKRUPCYTURNS = 3;
    public int price;
    public String name;
    public int sharesOwned; //number of shares that are sold and are brokers properties as of now
    public int curve10;
    private SortedArrayList<Pair<Time, Integer>> record;
    private boolean industryBoom;
    private int bankruptcyIndex;
    private boolean bankrupt;

    public Asset() {
        price = priceGen();
        name = Utils.assetNameGen();
        sharesOwned = 0;
        curve10 = 0;
        record = new SortedArrayList<>((o1, o2) -> {
            if (o1.getKey().day > o2.getKey().day) return 1;
            else return 0;
        });
        industryBoom = false;
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
            return true;
        }
        return false;
    }

    private void decreaseBIndex() {
        bankruptcyIndex = Math.max(0, bankruptcyIndex - 1);
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

    public void refreshPrice() {
        if (industryBoom) {
            price += Utils.randomNum(10) * (curve10 * 2 + 10);
            decreaseBIndex();
        } else {
            price += Utils.randomNum(10) * curve10;
        }
        industryBoom = Utils.randomNum(10) > 9;

        if (sharesOwned == 0 || record.isEmpty() || sharesOwned < (int) 0.1 * Game.getTimeClone().day) {
            bankruptcyIndex++;
        }

        if (bankruptcyIndex >= BANKRUPCYTURNS) {
            bankrupt = true;
        }

    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public String toString() {
        return "Name: " + this.name + Utils.spaces(maxSquaringSize - this.name.length()) + " Price " + "$" + this.price + ".";
    }

    private int priceGen() {
        return Utils.randomNum(10 + (Game.t.day * 10)) + 20;
    }

    public void setBankrupt() {
        bankrupt = true;
    }
}