package model.trading.assetStates;

import model.trading.Asset;
import model.utils.Utils;

public class NormalState implements AssetState {

	@Override
	public int getNewPrice(Asset asset) {
		int price = asset.getPrice();
		if (asset.getCurve10() == 0) {
            int sign = Utils.randomNum(10);
            if (sign > 5) {
                sign = -1;
            } else {
                sign = 1;
            }
            price += sign * Utils.randomNum(10) * asset.getCurve10();
        } else {
            price += Utils.randomNum(10) * asset.getCurve10();
        }
		return price;
	}

	@Override
	public int getNewBankruptcyIdx(int currBankruptIdx) {
		if (Utils.randomNum(10) > 7) {
			return currBankruptIdx + 1;
		}
		return currBankruptIdx;		
	}

	@Override
	public int getNewIndustryTurns(int currIndustryTurn) {
		return 0;
	}

}
