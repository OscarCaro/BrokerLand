package model.trading;

import controller.Game;

public class Asset {

	int price;
	public String name;
	public boolean owned;
	
	Asset() {
		price = AssetPriceGen();
		name = AssetStringGen();
		owned = false;
	}
	
	Asset(int price, String name){
		this.name = name;
		this.price = price;
		owned = false;
	}
	
	public String toString () {
		return "Name: " + this.name + ". Price " + "$"+ this.price + ".";
	}



	public static int  AssetPriceGen() {
		 return (int) (Math.random() * (1000 + (Game.day * 100))) + 37;
	}
	
	private String  AssetStringGen() {
		String name ="";
		int length = (int) (Math.random() * 5);
		for (int i = -2; i<length; i++) {
			name += (char)((int) (Math.random() * 25 + 65));
		}
		return name;
	}
}