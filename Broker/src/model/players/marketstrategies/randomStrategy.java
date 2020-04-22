package model.players.marketstrategies;

import model.players.Bot;
import model.trading.Asset;
import model.trading.Market;
import model.utils.Utils;

public class randomStrategy implements MarketStrategy {

    @Override
    public void buyAsset(Bot b) {
        Market maux = Market.getInstance();
        int rAsset = Utils.randomNum(maux.assets.size() - 1);
        Asset aaux = maux.assets.get(rAsset);
        while (aaux.price > b.getMoney()) {
            rAsset = Utils.randomNum(maux.assets.size() - 1);
            aaux = maux.assets.get(rAsset);
        }
        int rQuant = Math.max(Utils.randomNum(b.getMoney() / aaux.price), 1); //He buys either 1 or a random amount he can afford
        if (!b.playerBuyAsset(rAsset, rQuant)) {
            throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
        }
        System.out.println(b.getName() + " rashly bought " + rQuant + " shares of " + aaux.name + ".");
    }

    @Override
    public void sellAsset(Bot b) {
        int rAsset = Math.max(Utils.randomNum(b.getPortfolio().size() - 1), 0);
        Asset aaux = b.getPortfolio().get(rAsset).getKey();
        int rQuant = Math.max(Utils.randomNum(b.getPortfolio().get(rAsset).getValue()), 1); //He sells either 1 or a random amount he has
        if (!b.playerSellAsset(rAsset, rQuant)) {
            throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
        }
        System.out.println(b.getName() + " rashly sold " + rQuant + " shares of " + aaux.name + ".");
    }
}


