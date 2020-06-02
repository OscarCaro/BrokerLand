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
            price += sign * Utils.randomNum(10) * (asset.getCurve10() + Utils.randomNum(4));
        } else {
            price += Utils.randomNum(10) * (asset.getCurve10() + Utils.randomNum(4));
        }
        return price;
    }

    @Override
    public AssetState getNextState(Asset asset) {
        if (Utils.randomNum(10) > 8) {            // By chance
            return new IndustryBoomState();
        } else if (Utils.randomNum(10) > 7) {            // By chance
            return new IndustryCrashState();
        } else if (asset.getPrice() <= 0) {
            return new BankruptState();
        } else {
            return this;
        }
    }

}
