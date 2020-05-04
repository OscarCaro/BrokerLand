package model.trading.assetStates;

import model.trading.Asset;
import model.utils.Utils;

public class IndustryCrashState implements AssetState {
	
	private final int MAXCYCLES = Math.max(Utils.randomNum(4), 2);  //3 or 2 turns randomly
	private int cycleCounter;
	
	public IndustryCrashState () {
		cycleCounter = 0;
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
		asset.incrementBIndex();
		
		cycleCounter++;
		if (cycleCounter > MAXCYCLES) {
			return new NormalState();
		}
		else {
			return this;
		}
	}


}
