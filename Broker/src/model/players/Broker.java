package model.players;

import controller.Game;
import model.locations.LocationChanger;
import model.locations.WorldMap;
import model.trading.Asset;
import model.trading.Market;
import model.utils.Pair;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Broker extends Player {

    private Scanner in;

    public Broker(LocationChanger map, Market globalMarket) {
        super("You", "", map, WorldMap.HOMEIDX, 1000, globalMarket);
        in = new Scanner(System.in);
    }

    public void update() {
//    	System.out.println(ownTime + ". You're now at: "+ currLoc);		To be used when we add more players (MODS)
        System.out.println("------------------------------------------------------------------");
        System.out.println(Game.t + ". You're now at: " + currLoc);
        System.out.println("------------------------------------------------------------------");
        System.out.println(mentalH + " Money:" + money);
        System.out.println("------------------------------------------------------------------");
        showPortfolio();
        System.out.println("------------------------------------------------------------------\n");
        askActions();
        askNewLocation();
    }

    private void askActions() {
        System.out.println("What do you want to do?");
        currLoc.printActions();
        System.out.println("Select: ");
        String aux = in.nextLine();
        while (Integer.parseInt(aux) < 0 || Integer.parseInt(aux) >= currLoc.getNumOfActions()) {
            System.out.println("Invalid option");
            System.out.println("What do you want to do?");
            aux = in.nextLine();
        }
        currLoc.performAction(this, Integer.parseInt(aux), true);
    }

    private void askNewLocation() {
//    	System.out.println(ownTime + ". You're now at: "+ currLoc);		To be used when we add more players (MODS)
        System.out.println(Game.t + ". You're now at: " + currLoc);
        System.out.println("\nDo you want to move? (y/n)");
        String input = in.nextLine();
        if (input.toUpperCase().equals("Y")) {
            map.printLocations();
            System.out.println("Where to?");
            input = in.nextLine();
            while (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= map.getNumOfLocs()) {
                System.out.println("Invalid option");
                System.out.println("Where to?");
                input = in.nextLine();
            }
            map.moveTo(this, Integer.parseInt(input));
        }
    }


    public String endMessage() {
        String aux = null;
        if (money < 0) {
            aux = "You went bankrupt and your addiction to cocaine made "
                    + "you go into rehab,\n you're now starting a new life in the fields of Maine.\n";
        } else if (mentalH.insane())
            aux = "The life you chose has seemed unbearable for a time now,\n" +
                    " and after considering suicide several times too many you decide to get help.\n" +
                    " The first thing you do to change your life around is give up trading.\n ";
        aux += "\n-------------- You are done ---------------";
        return aux;
    }

    @Override
    public void buy() {
        globalMarket.print();
        System.out.println("Do you want to buy any asset? (y/n)");
        String input = in.nextLine();
        while (input.toUpperCase().equals("Y")) {
            mentalH.add(-10);
            System.out.println("Which one? (0->" + (globalMarket.getNumOfAssets() - 1) + ")");
            input = in.nextLine();
            int idx = Integer.parseInt(input);
            System.out.println("How many? (1->" + (this.money / globalMarket.assets.get(idx).price) + ")");
            input = in.nextLine();
            int qtty = Integer.parseInt(input);
            if (!globalMarket.buy(this, idx, qtty)) {
                System.out.println("You cannot buy that asset");
            }
            else{
                portfolio.add(new Pair<>(globalMarket.assets.get(idx), qtty));
            }
        }
        showPortfolio();
        System.out.println("Do you want to buy any more assets? (y/n)");
        input = in.nextLine();
        if (input.toUpperCase().equals("Y")) {
            globalMarket.print();
        }
    }

    @Override
    public void sell() {
        showPortfolio();
        if (portfolio.size() > 0) {
            System.out.println("Do you want to sell any? (y/n)");
            String input = in.nextLine();
            while (!input.toUpperCase().equals("N")) {
                mentalH.add(-10);
                System.out.println("Which one? (index)");
                input = in.nextLine();
                int idx = Integer.parseInt(input);
                while (idx < 0 && idx >= portfolio.size()) {
                    System.out.println("Invalid option. Which one? (index)");
                    input = in.nextLine();
                    idx = Integer.parseInt(input);
                }
                System.out.println("How many? (quantity)");
                input = in.nextLine();
                int qtty = Integer.parseInt(input);
                while (qtty < 0 && qtty > portfolio.get(idx).getValue()) {
                    System.out.println("Invalid option. How many? (quantity)");
                    input = in.nextLine();
                    qtty = Integer.parseInt(input);
                }
                globalMarket.sell( this, globalMarket.marketIndex(portfolio.get(idx).getKey()), qtty);
                Pair<Asset, Integer> p = new Pair<>(portfolio.get(idx).getKey(), portfolio.get(idx).getValue()-qtty);
                portfolio.remove(idx);
                if (p.getValue() > 0){
                    portfolio.add(p);
                } //If I did not sell all of them I must keep the pair with the new value of qtty
                showPortfolio();
                if (portfolio.size() > 0) {
                    System.out.println("Do you want to sell any others? (y/n)");
                    input = in.nextLine();
                }
                else{
                    input = "N"; //it HAS to break, but i dont want to insert break chain.
                }
            }
        }
    }


    private void showPortfolio() {
        if (portfolio.size() == 0) {
            System.out.println("You currently do not own any assets");
        } else {
            System.out.println("Your assets on the market sit currently at: ");
            int i = 0;
            for (Pair<Asset, Integer> p : portfolio) {
                System.out.println("\"-----------------------------------------------------------");
                System.out.println(i + ": " + p.getKey().name + "| Selling at: " + p.getKey().price + "| Owned "+ p.getValue() + " of them." );
                i++;
            }
            System.out.println("-----------------------------------------------------------\n");

            System.out.println("Your portfolio now amounts to: " + "$" + money);
        }
    }
}
