package model.trading;

import controller.Game;
import model.players.Player;
import model.utils.Utils;

public class Asset {

    private static final int maxSquaringSize = 18;
    public int price;
    public String name;
    public int sharesOwned; //number of shares that are sold and are brokers properties as of now

    public Asset() {
        price = priceGen();
        name = Utils.assetNameGen();
        sharesOwned = 0;
    }

    public boolean buy(Player player, int quantity) {
        int fullp = price * quantity;
        if (player.getMoney() >= fullp) {
            player.modifyMoney(-1 * fullp);
            sharesOwned += quantity;
            return true;
        }
        return false;
    }

    public boolean sell(Player player, int quantity) {
        player.modifyMoney(price * quantity);
        return true;
    }

    public void refreshPrice() {
        price = priceGen();
    }

    public String toString() {
        return "Name: " + this.name + Utils.spaces(maxSquaringSize - this.name.length()) + " Price " + "$" + this.price + ".";
    }

    private int priceGen() {
        return Utils.randomNum(10 + (Game.t.day * 10)) + 20;
    }
}