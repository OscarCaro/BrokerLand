package model.trading.assetStates;

import model.trading.Asset;

public interface AssetState {
	
	 int getNewPrice(Asset asset);
	 AssetState getNextState(Asset asset);

}
