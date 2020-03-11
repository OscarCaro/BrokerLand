package model.trading;

import java.util.*;

import model.players.Player;

public class Market{

	public List<Asset> assets;
	
	public Market() {
		assets = new ArrayList<Asset>();
		assets.add(new Asset());
		assets.add(new Asset());
		assets.add(new Asset());
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
	
	public List<Asset> getAssetsOwnedBy(Player player) {
		List<Asset> subList = new ArrayList<>();
		for(Asset asset : assets) {
			if (asset.isOwnedBy(player)) {
				subList.add(asset);
			}
		}
		return subList;
	}
	
	public boolean buy(Player player, int assetIdx) {
		if(assetIdx >= 0 && assetIdx < assets.size()) {
			return assets.get(assetIdx).buy(player);
		}
		return false;
	}
	
	public boolean sell(Player player, int assetIdx) {
		if(assetIdx >= 0 && assetIdx < assets.size()) {
			return assets.get(assetIdx).sell(player);
		}
		return false;
	}
	
	public boolean isAssetOwnedBy (Player player, int assetIdx) {
		if(assetIdx >= 0 && assetIdx < assets.size()) {
			return assets.get(assetIdx).isOwnedBy(player);
		}
		return false;
	}
	
	public int getNumOfAssets() {
		return assets.size();
	}
	
}
