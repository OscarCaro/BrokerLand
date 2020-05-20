package model.trading.assetStates;

import model.trading.Asset;
import model.utils.Utils;

public class IndustryBoomState implements AssetState {

	private int maxCycles;
	private int cycleCounter;
	
	public IndustryBoomState() {
		cycleCounter = 0;
		maxCycles = Math.max(Utils.randomNum(4), 2);  //3 or 2 turns randomly
	}

	@Override
	public int getNewPrice(Asset asset) {
		int price = asset.getPrice();
        price += Math.abs(Utils.randomNum(10) * (asset.getCurve10() * 2 + 10));
		return price;
	}

	@Override
	public AssetState getNextState(Asset asset) {
		asset.decreaseBIndex();
		cycleCounter++;
		if (cycleCounter > maxCycles) {
			return new NormalState();
		}
		else {
			return this;
		}
	}

}
