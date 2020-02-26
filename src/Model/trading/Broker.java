package model.trading;

import controller.Game;
import model.life.MentalHealth;
import model.locations.Locatable;
import model.locations.Location;

import java.util.Scanner;

public class Broker extends Locatable {
    public static Scanner in;
    public int money;
    protected MentalHealth mental;

    public Broker() {
        super(Location.HOME);
        mental = new MentalHealth(100);
        money = 1000;
        in = new Scanner(System.in);
    }

    public void update() {
        if (isIn(Location.HOME)) {
            eat();
        } else if (isIn(Location.OFFICE)) {
            buy();
            refresh();
            showAssets();
            sell();
        }
        askMove();

    }

    public void eat() {
        System.out.println("you ate");
        mental.add(15);
    }


    public void buy() {
        System.out.println("Do you want to buy any asset? (y/n)");
        String input = in.nextLine();
        while (input.toUpperCase().equals("Y")) {
            mental.add(-10);
            System.out.println("Which one? (0->" + (Market.size - 1) + ")");
            input = in.nextLine();
            if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < Market.size) {
                if (Market.assets.get(Integer.parseInt(input)).owned) {
                    System.out.println("You already own " + Market.assets.get(Integer.parseInt(input)).name);
                } else {
                    money -= Market.assets.get(Integer.parseInt(input)).price;
                    Market.assets.get(Integer.parseInt(input)).owned = true;
                }
            }
            System.out.println("Do you want to buy any more assets? (y/n)");
            input = in.nextLine();
            if (input.toUpperCase().equals("Y")) {
                Market.showState();
            }

        }
    }


    public void sell() {
        if (Market.getOwned() > 0) {
            System.out.println("Do you want to sell any? (y/n)");
            String input = in.nextLine();
            if (input.toUpperCase().equals("Y")) {
                mental.add(-20);
                while (!input.toUpperCase().equals("N") && Market.getOwned() > 0) {
                    System.out.println("Which one? (index)");
                    input = in.nextLine();
                    money += Market.assets.get(Integer.parseInt(input)).price;
                    Market.assets.get(Integer.parseInt(input)).owned = false;
                    showAssets();
                    if (Market.getOwned() > 0) {
                        System.out.println("Do you want to sell any others? (y/n)");
                        input = in.nextLine();
                    }
                }

            }
        }
    }


    public void refresh() {
        for (int i = 0; i < Market.size; i++) {
            if (Market.assets.get(i).owned) {
                Market.assets.get(i).price = Math.abs(Market.assets.get(i).price - (37 + 100 + (Game.t.day)));
            }
        }
    }

    public void showAssets() {
        if (Market.getOwned() == 0) {
            System.out.println("You currently do not own any assets");
        } else {
            System.out.println("Your assets on the market sit currently at: ");
            for (int i = 0; i < Market.size; i++) {
                if (Market.assets.get(i).owned) {
                    System.out.println("-------------------------------------");
                    System.out.println(i + ": " + Market.assets.get(i));
                }
            }
            System.out.println("-------------------------------------\n");

            System.out.println("Your portfolio now amounts to: " + "$" + money);
        }
    }

    public void askMove() {
        System.out.println("You're currently at the " + loc.toString() + "\nDo you want to move? (y/n)");
        String input = in.nextLine();
        if (input.toUpperCase().equals("Y")) {
            Location.printAll();
            System.out.println("Where to?");
            input = in.nextLine();
            if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < Location.totalValue()) {
                Location l = Location.parse(Integer.parseInt(input));
                this.go(l);
            }
        }

    }

    public void addMental(int m) {
        mental.add(m);
    }

    public boolean checkHealth() {
        return money > 0 && !mental.insane();
    }

    public void showLife() {
        System.out.println("-------------------------------------");
        System.out.println("Your portfolio amounts to: " + "$" + money);
        System.out.println(mental);
        System.out.println("-------------------------------------");
    }

    public String end() {
        String aux = null;
        if (money < 0) {
            aux = "You went bankrupt and your addiction to cocaine made "
                    + "you go into rehab,\n you're now starting a new life in the fields of Maine.\n";
        } else if (mental.insane())
            aux = "The life you chose has seemed unbearable for a time now,\n" +
                    " and after considering suicide several times too many you decide to get help.\n" +
                    " The first thing you do to change your life around is give up trading.\n ";
        aux += "\n-------------- You are done ---------------";
        return aux;
    }
}
