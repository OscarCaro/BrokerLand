package Model.trading;
import Model.trading.*;
import Model.locations.*;
import Model.Life.*;

import java.util.Scanner;

import controller.Game;
import controller.main;

public class Broker extends Locatable{
	public int money;
	static Scanner in;
	protected MentalHealth mental;
	public Broker() {
		super(Location.HOME);
		mental = new MentalHealth(100);
		money = 1000;
		in = new Scanner(System. in);
	}
	
	public void update() {
		if (isIn(Location.HOME)) {
			eat();
		}
		else if (isIn(Location.OFFICE)) {
			buy();
			refresh();
			showState();
			sell();
		}		
		askMove();

	}
	
	public void eat() {
		System.out.println("you ate");
		mental.add(15);
	}
	
	
	public void buy () {
		System.out.println("Do you want to buy any asset? (y/n)");
		String input = in.nextLine();
		while(input.toUpperCase().equals("Y")) {
			mental.add(-10) ;
			System.out.println("Which one? (0->"+ (Market.size-1)+ ")");
			input = in.nextLine();
			if (Integer.parseInt(input) >=0 && Integer.parseInt(input) < Market.size) {
				if(Market.assets[Integer.parseInt(input)].owned) {
					System.out.println("You already own "+Market.assets[Integer.parseInt(input)].name );
				}
				else {
					money -= Market.assets[Integer.parseInt(input)].price;
					Market.assets[Integer.parseInt(input)].owned = true;
				}
			}
			System.out.println("Do you want to buy any more assets? (y/n)");
			input = in.nextLine();
			if(input.toUpperCase().equals("Y")) {
				Market.showState();	
			}
			
		}
	}
		
		
	public void sell(){
		if(Market.getOwned()>0) {
		System.out.println("Do you want to sell any? (y/n)");
		String input = in.nextLine();
		if (input.toUpperCase().equals("Y")) {
			mental.add(-20) ;
			while(!input.toUpperCase().equals("N")&& Market.getOwned()>0) {
				System.out.println("Which one? (index)");
				input = in.nextLine();
				money += Market.assets[Integer.parseInt(input)].price;
				Market.assets[Integer.parseInt(input)].owned = false;
				showState();
				if (Market.getOwned()>0) {
					System.out.println("Do you want to sell any others? (y/n)");
					input = in.nextLine();
				}
			}
			
		}
		}
	}
	
	
	public void refresh() {
		for(int i = 0; i < Market.size; i++) {
			 Market.assets[i].price = (int) (Math.random() * (1000 + (Game.turn * 100))) + 37;
		}
		for(int i = 0; i < Market.size; i++) {
			if (Market.assets[i].owned) {
			 Market.assets[i].price = Math.abs( Market.assets[i].price -(37 + 100 + (Game.turn)));
			}
		}
	}
	
	public void showState() {
		if(Market.getOwned()==0) {
			System.out.println("You currently do not own any assets");
		}
		else {
		System.out.println("Your assets on the market sit currently at: ");
		for(int i = 0; i < Market.size; i++) {
			if(Market.assets[i].owned) {
				 System.out.println("-------------------------------------");
			 System.out.println(i + ": "+ Market.assets[i]);
			}
		}
		 System.out.println("-------------------------------------\n");

			System.out.println("Your portfolio now amounts to: " + "$" + money );
		}
	}
	
	public void askMove() {
		System.out.println("You're currently at the " + loc.toString() + "\nDo you want to move? (y/n)");
		String input = in.nextLine();
		if (input.toUpperCase().equals("Y")) {
			Location.printAll();
			System.out.println("Where to?");
			input = in.nextLine();
			if(Integer.parseInt(input) > 0 && Integer.parseInt(input) < Location.totalValue()) {
				Location l = Location.parse(Integer.parseInt(input));
				this.go(l);
			}
		}
	
	}
	
	public boolean checkHealth() {
		return money > 0 && mental.insane(); 
	}
	
	public void showLife() {
		System.out.println("Your portfolio amounts to: " + "$" + money);
		System.out.println(mental);
	}
}
