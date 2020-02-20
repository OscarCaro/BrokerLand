package controller;

import java.util.ArrayList;

import Model.locations.Locatable;
import Model.trading.Broker;
import Model.trading.Market;

public class Game {
	
	private ArrayList<Locatable> list;
	private Broker player;
	public static int turn;
	private Market market;
	
	public Game(){
		list = new ArrayList<>();
		player = new Broker();
		list.add(player);
		
		market = new Market();
		market.initMarket();
		list.add(market);
		
		turn = 0;
	}
	
	public void run() {
		while(player.money > 0) {
			market.opportunity();
			showState();
			market.showState();
			player.update();
//			player.buy();
//			player.refresh();
//			player.sell();
//			player.askMove();
			turn++;
		}
		System.out.println("You went bankrupt and your addiction to cocaine made "
				+ "you go into rehab,\n you're now starting a new life in the fields of Maine.");
	}
	
	
	public static void showState() {
		System.out.println("Turn: " + turn + ".");
	}
}
