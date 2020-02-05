import java.util.Scanner;

public class Broker {
	static  int money;
	static Scanner in;
	//private Asset assets[];
	
	public Broker() {
		//assets = new Asset[20];
		money = 1000;
		in = new Scanner(System. in);
	}
	
	public static void buy () {
		int sel = (int) (Math.random() * Market.size -1);
		System.out.println("Do you want to buy some " + Market.assets[sel].name + " at the price of " + Market.assets[sel].price + "? (y/n)");
		String input = in.nextLine();
		if (input.toUpperCase().equals("Y")) {
			money -= Market.assets[sel].price;
			Market.assets[sel].owned = true;
		}
	}
		
	public void sell(){
		if(Market.getOwned()>0) {
		System.out.println("Do you want to sell any? (y/n)");
		String input = in.nextLine();
		if (input.toUpperCase().equals("Y")) {
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
			 Market.assets[i].price = (int) (Math.random() * (1000 + (main.turn * 100))) + 37;
		}
		for(int i = 0; i < Market.size; i++) {
			if (Market.assets[i].owned) {
			 Market.assets[i].price -= (10+main.turn*20);
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

			System.out.println("Your portfolio now amounts to: " + money + "$.");
		}
	}
	
	
}
