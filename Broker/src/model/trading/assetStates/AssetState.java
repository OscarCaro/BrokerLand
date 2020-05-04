package model.trading.assetStates;

import model.trading.Asset;

public interface AssetState {
	
	public int getNewPrice(Asset asset);
	public AssetState getNextState(Asset asset);

}
