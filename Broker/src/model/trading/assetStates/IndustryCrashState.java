package model.trading.assetStates;

import model.trading.Asset;
import model.utils.Utils;

public class IndustryCrashState implements AssetState {

	@Override
	public int getNewPrice(Asset asset) {
		int price = asset.getPrice();
        price += Math.min(Utils.randomNum(10) * (asset.getCurve10() * 2 + 10),
        					-Utils.randomNum(10) * (asset.getCurve10() * 2 + 10));
		return price;
	}

	@Override
	public int getNewBankruptcyIdx(int currBankruptIdx) {
		return currBankruptIdx + 1;
	}

	@Override
	public int getNewIndustryTurns(int currIndustryTurn) {
		return currIndustryTurn + 1;
	}

}
