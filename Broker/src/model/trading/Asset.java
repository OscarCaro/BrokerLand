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
        name = stringGen();
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

    private String stringGen() {
        int type = Utils.randomNum(6);
        StringBuilder name = new StringBuilder("" + Utils.randomLetter()); /*first an Upper case letter.*/
        if (type == 0) {
            int length = Utils.randomNum(2) + 1;
            char aux;
            aux = Utils.randomLetter();
            if (aux == 'A' || aux == 'O' || aux == 'I' || aux == 'E' || aux == 'U') {
                name.append(Character.toLowerCase(aux));
                name.append(Character.toLowerCase(Utils.randomConsonant()));
            } else {
                name.append(Character.toLowerCase(Utils.randomVowel()));
                name.append(Character.toLowerCase(aux));
            }
            name.append(" Electricity");
        } else if (type == 1) { //Incorporated companies
            int length = Utils.randomNum(3) + 2;
            for (int i = 0; i < length; i++) {
                name.append(Utils.randomLetter());
            }
            name.append(" Inc");
        } else if (type == 2) { //Limited Liability Company asset
            int length = Utils.randomNum(3) + 1;
            for (int i = 0; i < length; i++) {
                name.append(Utils.randomLetter());
            }
            name.append(" LLC");
        } else if (type == 3) { //Crypto Assets
            int length = Utils.randomNum(2);
            char aux;
            for (int i = 0; i < length; i++) {
                aux = Utils.randomLetter();
                if (aux == 'A' || aux == 'O' || aux == 'I' || aux == 'E' || aux == 'U') {
                    name.append(Character.toLowerCase(aux));
                    name.append(Character.toLowerCase(Utils.randomConsonant()));
                } else {
                    name.append(Character.toLowerCase(Utils.randomVowel()));
                    name.append(Character.toLowerCase(aux));
                }
            }
            if (name.toString().length() == 1) {
                StringBuilder auxS = new StringBuilder("The ");
                auxS.append(name);
                name = auxS;
            }
            name.append(" Coin");
        } else if (type == 4) {//Cooperatives
            int oneMore, length = Utils.randomNum(2) + 1;
            char aux;
            aux = Utils.randomLetter();
            for (int i = 0; i < length; i++) {
                if (aux == 'A' || aux == 'O' || aux == 'I' || aux == 'E' || aux == 'U') {
                    name.append(Character.toLowerCase(aux));
                    name.append(Character.toLowerCase(Utils.randomConsonant()));
                } else {
                    name.append(Character.toLowerCase(Utils.randomVowel()));
                    name.append(Character.toLowerCase(aux));
                    oneMore = Utils.randomNum(2);
                    if (oneMore == 0) {
                        name.append(Character.toLowerCase(Utils.randomConsonant()));
                    }
                }
            }
            name.append(" Co");
        } else { //random assets or resources
            int oneMore, length = Utils.randomNum(3) + 1;
            char aux;
            for (int i = 0; i < length; i++) {
                aux = Utils.randomLetter();
                if (aux == 'A' || aux == 'O' || aux == 'I' || aux == 'E' || aux == 'U') {
                    name.append(Character.toLowerCase(aux));
                    name.append(Character.toLowerCase(Utils.randomConsonant()));
                } else {
                    name.append(Character.toLowerCase(Utils.randomVowel()));
                    name.append(Character.toLowerCase(aux));
                    oneMore = Utils.randomNum(5);
                    if (oneMore == 0) {
                        name.append(Character.toLowerCase(Utils.randomConsonant()));
                    }
                }
            }
        }


        return name.toString();
    }
}