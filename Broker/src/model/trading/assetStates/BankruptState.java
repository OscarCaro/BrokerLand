package model.trading.assetStates;

import model.trading.Asset;
import model.utils.Utils;

public class BankruptState implements AssetState {

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
	public AssetState getNextState(Asset asset) {
		return this;
	}

}