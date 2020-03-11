package model.trading;

import controller.Game;
import model.players.Player;

public class Asset {

	public int price;
	public String name;
	private Player owner;
	
	public Asset() {
		price = priceGen();
		name = stringGen();
		owner = null;
	}
	
	public boolean isAvailable() {
		return owner != null;
	}
	
	public boolean isOwnedBy(Player player) {
		return owner != null && owner.equals(player);
	}
	
	public boolean buy(Player player) {
		if(owner == null && player.getMoney() >= price) {
			owner = player;
			player.modifyMoney(-1 * price);
			return true;
		}
		return false;
	}
	
	public boolean sell(Player player) {
		if (owner != null && owner.equals(player)) {
			owner.modifyMoney(price);
			owner = null;
			return true;
		}
		return false;
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