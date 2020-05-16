package model.trading.assetStates;

import model.trading.Asset;

public class BankruptState implements AssetState {

	@Override
	public int getNewPrice(Asset asset) {
		return Math.abs(asset.getPrice());
	}

	@Override
	public AssetState getNextState(Asset asset) {
		return this;
	}

}
