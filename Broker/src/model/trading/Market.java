package model.trading;

import java.util.*;

import model.players.Player;

//This class follows the Singleton pattern, since there should be only one Market
public class Market{
	
	private static Market instance;

	public List<Asset> assets;
	
	private Market() {
		assets = new ArrayList<Asset>();
		assets.add(new Asset());
		assets.add(new Asset());
		assets.add(new Asset());
	}
	
	public static Market getInstance() {
		if (instance == null) {
			instance = new Market();
		}
		return instance;
	}

	public void refresh() {
		for(Asset asset : assets) {
			asset.refreshPrice();
		}
	}
	
	public void opportunity() {
		assets.add(new Asset());
	}
	
	public void print() {
		System.out.println("The market sits currently at: ");		
		for(int i = 0; i < assets.size(); i++) {
			System.out.println(i + ": " + assets.get(i) );
		}
	}
	
	public boolean buy(Player player, int assetIdx, int quantity) {
		if(assetIdx >= 0 && assetIdx < assets.size()) {
			return assets.get(assetIdx).buy(player, quantity);
		}
		return false;
	}
	
	public boolean sell(Player player, int assetIdx, int quantity) {
		if(assetIdx >= 0 && assetIdx < assets.size() && quantity >= assets.get(assetIdx).sharesOwned) {
			return assets.get(assetIdx).sell(player, quantity);
		}
		return false;
	}

	public int getNumOfAssets() {
		return assets.size();
	}

	public int marketIndex(Asset key) {
		for (int i = 0; i < assets.size(); i++){
			if (key.equals(assets.get(i))) return i;
		}
		throw new ArrayIndexOutOfBoundsException("Asset is not in market.");
	}

}
