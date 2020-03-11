package model.players;

import model.locations.LocationChanger;
import model.locations.Map;
import model.trading.Asset;
import model.trading.Market;

import java.util.List;
import java.util.Scanner;

import controller.Game;

public class Broker extends Player {
	
    private Scanner in;

    public Broker(LocationChanger map, Market globalMarket) {
    	super(map, Map.HOMEIDX, 1000, globalMarket);
        in = new Scanner(System.in);
    }

    public void update() {
//    	System.out.println(ownTime + ". You're now at: "+ currLoc);		To be used when we add more players (MODS)
    	System.out.println(Game.t + ". You're now at: "+ currLoc);
    	System.out.println(mentalH + " Money:" + money);
    	askActions();
    	askNewLocation();
    }
    
    private void askActions() {
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
//    	System.out.println(ownTime + ". You're now at: "+ currLoc);		To be used when we add more players (MODS)
    	System.out.println(Game.t + ". You're now at: "+ currLoc);
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
            if (globalMarket.isAssetOwnedBy(this, idx)) {
                System.out.println("You already own that asset");
            } else {
                if(!globalMarket.buy(this, idx)) {
                	System.out.println("You cannot buy that asset");
                	//Price too high or already owned by other player
                }
            }
            
            System.out.println("Do you want to buy any more assets? (y/n)");
            input = in.nextLine();
            if (input.toUpperCase().equals("Y")) {
                globalMarket.print();
            }
        }
    }

    @Override
    public void sell() {
    	List<Asset> myAssets = globalMarket.getAssetsOwnedBy(this);
    	showAssets(myAssets);
        if (myAssets.size() > 0) {
            System.out.println("Do you want to sell any? (y/n)");
            String input = in.nextLine();
            
            while (!input.toUpperCase().equals("N") && myAssets.size() > 0) {
            	mentalH.add(-10);
            	System.out.println("Which one? (index)");
                input = in.nextLine();
                
                int idx = Integer.parseInt(input);
                while(idx < 0 && idx >= myAssets.size()) {
                	System.out.println("Invalid option. Which one? (index)");
                    input = in.nextLine();
                }
                
                myAssets.get(idx).sell(this);
                myAssets.remove(idx);
                
                showAssets(myAssets);
                if (myAssets.size() > 0) {
                    System.out.println("Do you want to sell any others? (y/n)");
                    input = in.nextLine();
                }
            }
        }
    }
    
    private void showAssets(List<Asset> myAssets) {    	
    	
        if (myAssets.size() == 0) {
            System.out.println("You currently do not own any assets");
        } else {
            System.out.println("Your assets on the market sit currently at: ");
            for (int i = 0; i < myAssets.size(); i++) {                
                System.out.println("-------------------------------------");
                System.out.println(i + ": " + myAssets.get(i));                
            }
            System.out.println("-------------------------------------\n");

            System.out.println("Your portfolio now amounts to: " + "$" + money);
        }
    }
}
