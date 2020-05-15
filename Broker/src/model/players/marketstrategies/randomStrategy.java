package model.players.marketstrategies;

import model.players.Bot;
import model.trading.Asset;
import model.trading.Market;
import model.utils.Utils;

public class randomStrategy extends MarketCommonKnowledge implements MarketStrategy  {

    @Override
    public void buyAsset(Bot b) {
        if (Market.getInstance().canBuyAnyWith(b.getMoney())) {
            Market maux = Market.getInstance();
            int rAsset = Utils.randomNum(maux.assets.size());
            Asset aaux = maux.assets.get(rAsset);
            while (aaux.price > b.getMoney()) {
                rAsset = Utils.randomNum(maux.assets.size());
                aaux = maux.assets.get(rAsset);
            }
            int rQuant = Math.max(Utils.randomNum(b.getMoney() / aaux.price), 1); // buys either 1 or a random amount affords
            if (!b.playerBuyAsset(rAsset, rQuant)) {
                throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
            }
            System.out.println(b.getName() + " rashly bought " + rQuant + " shares of " + aaux.name + ".");
        }
        else{
            System.out.println(b.getName() + " wants to buy shares but is very short on cash, what a joker.");
        }
    }

    @Override
    public void sellAsset(Bot b) {
        int rAsset = Utils.randomNum(b.getPortfolio().size());
        Asset aaux = b.getPortfolio().get(rAsset).getKey();
        int rQuant = Math.max(Utils.randomNum(b.getPortfolio().get(rAsset).getValue()), 1); //He sells either 1 or a random amount in ownership
        if (!b.playerSellAsset(rAsset, rQuant)) {
            throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
        }
        System.out.println(b.getName() + " rashly sold " + rQuant + " shares of " + aaux.name + ".");
    }

    @Override
    public void sellAssetDebt(Bot b) {
        while(b.getMoney() <0) {
            int rAsset = Utils.randomNum(b.getPortfolio().size());
            Asset aaux = b.getPortfolio().get(rAsset).getKey();
            int rQuant = Math.max(Utils.randomNum(b.getPortfolio().get(rAsset).getValue()), 1); //He sells either 1 or a random amount in ownership
            if (!b.playerSellAsset(rAsset, rQuant)) {
                throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
            }
            System.out.println(b.getName() + " rashly sold " + rQuant + " shares of " + aaux.name + " to escape debt.");
        }
    }
}