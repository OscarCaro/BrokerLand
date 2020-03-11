package model.players;

import model.locations.Location;
import model.locations.LocationChanger;

import java.util.Scanner;

import controller.Game;

public class Broker extends Player {
	
    private Scanner in;

    public Broker(LocationChanger map, Market globalMarket) {
    	super(map, map.getHomeObj(), 1000, globalMarket);
        in = new Scanner(System.in);
    }

    public void update() {
    	System.out.println(ownTime + ". You're now at: "+ currLoc);
    	System.out.println(mentalH + " Money:" + money);
    	askActions();
    	askNewLocation();
    }
    
    public void refresh() {
    	//TODO: re-implement when market is corrected
    	for (int i = 0; i < Market.size; i++) {
    		if (Market.assets.get(i).owned) {
    			Market.assets.get(i).price = Math.abs(Market.assets.get(i).price - (37 + 100 + (Game.t.day)));
    		}
    	}
    }
    
    public void askActions() {
    	System.out.println("-------------------------------------");
        System.out.println("What do you want to do?");
    	currLoc.printActions();
    	
    	System.out.println("Select: ");
    	String aux = in.nextLine();
        while (Integer.parseInt(aux) < 0 || Integer.parseInt(aux) >= currLoc.getNumOfActions()) {
        	System.out.println("Invalid option");
        	System.out.println("What do you want to do?");
        	aux = in.nextLine();
        }
        currLoc.performAction(this, Integer.parseInt(aux));    	
    }
    
	private void askNewLocation() {		
		System.out.println(ownTime + ". You're now at: "+ currLoc);
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

    public void buy() {
    	// TODO: re-implement once Market is ok
    	Market.showState();
        System.out.println("Do you want to buy any asset? (y/n)");
        String input = in.nextLine();
        while (input.toUpperCase().equals("Y")) {
            mentalH.add(-10);
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
    	// TODO: re-implement once Market is ok
    	showAssets();
        if (Market.getOwned() > 0) {
            System.out.println("Do you want to sell any? (y/n)");
            String input = in.nextLine();
            if (input.toUpperCase().equals("Y")) {
                mentalH.add(-20);
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
    public void showAssets() {
    	// TODO: re-implement once Market is ok
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
}
