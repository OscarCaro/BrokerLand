package model.trading;

import controller.Game;
import model.locations.*;

import java.util.*;

public class Market extends Locatable{

	public static List<Asset> assets;
	public static int size;
	public Market() {
		super(Location.OFFICE);
		
		assets = new ArrayList<Asset>();
	}

	public static void refresh() {
		for (int i = 0; i < Market.size; i++) {
			Market.assets.get(i).price = Asset.AssetPriceGen();
		}
	}

	public void initMarket() {
		assets.add(new Asset());
		assets.add(new Asset());
		assets.add(new Asset());
		size = 3;
	}
	
	public void addAsset(Asset a) {
		assets.add(a);
		size++;
	}
	
	public static void opportunity() {
		assets.add(new Asset());
		size++;
	}
	
	public static void showState() {
		System.out.println("The market sits currently at: ");
		for(int i = 0; i < size; i++) {
			System.out.println(i + ": " + assets.get(i) );
		}
	}
	
	public static int getOwned () {
		int a = 0;
		for(int i = 0; i < Market.size; i++) {
			if(assets.get(i).owned) {
				 a++;
			}
		}
		return a;
	}
}
