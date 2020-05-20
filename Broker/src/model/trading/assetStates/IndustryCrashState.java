package model.trading.assetStates;

import model.trading.Asset;
import model.utils.Utils;

public class IndustryCrashState implements AssetState {

    private int maxCycles;
    private int cycleCounter;

    public IndustryCrashState() {
        cycleCounter = 0;
        maxCycles = Math.max(Utils.randomNum(4), 2);  //3 or 2 turns randomly
    }

    @Override
    public int getNewPrice(Asset asset) {
        int price = asset.getPrice();
        price += Math.min(Utils.randomNum(10) * (asset.getCurve10() * 2 + 10),
                -Utils.randomNum(10) * (asset.getCurve10() * 2 + 10));
        return price;
    }

    @Override
    public AssetState getNextState(Asset asset) {
        if (Utils.randomNum(10) > 5) {
            asset.incrementBIndex();
        }
        cycleCounter++;
        if (asset.getPrice() <= 0) {
            return new BankruptState();
        }
        else if (cycleCounter > maxCycles) {
            return new NormalState();
        } else {
            return this;
        }
    }


}
