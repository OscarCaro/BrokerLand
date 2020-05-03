package model.trading.assetStates;

import model.trading.Asset;

public interface AssetState {
	
	public int getNewPrice(Asset asset);
	public int getNewBankruptcyIdx(int currBankruptIdx);
	public int getNewIndustryTurns(int currIndustryTurn);

}
