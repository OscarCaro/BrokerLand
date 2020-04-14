package model.trading;

import controller.Game;
import model.players.Player;

public class Asset {

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
		if(player.getMoney() >= fullp) {
			player.modifyMoney(- 1* fullp);
			sharesOwned += quantity;
			return true;
		}
		return false;
	}
	
	public boolean sell(Player player, int quantity) {
		player.modifyMoney(price*quantity);
		return true;
	}
	
	public void refreshPrice() {
		price = priceGen();
	}
	
	public String toString () {
		return "Name: " + this.name + ". Price " + "$"+ this.price + ".";
	}

	private int priceGen() {
		 return (int) (Math.random() * (1000 + (Game.t.day * 100))) + 37;
	}
	
	private String stringGen() {
		String name ="";
		int length = (int) (Math.random() * 5);
		for (int i = -2; i<length; i++) {
			name += (char)((int) (Math.random() * 25 + 65));
		}
		return name;
	}
}