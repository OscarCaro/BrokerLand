package model.players.marketstrategies;

import model.players.Bot;
import model.trading.Asset;
import model.trading.Market;
import model.utils.Pair;
import model.utils.Utils;

import java.util.Iterator;

public class dumbassStrategy extends MarketCommonKnowledge implements MarketStrategy  {

    @Override
    public void buyAsset(Bot b) {
        if (Market.getInstance().canBuyAnyWith(b.getMoney())) {
            Market maux = Market.getInstance();
            int rAsset = Utils.randomNum(maux.assets.size());
            Asset aaux = maux.assets.get(rAsset);
            boolean decidedOnABadBuy = false;
            for (int i = 0; i < maux.assets.size() && !decidedOnABadBuy; i++) {
                rAsset = Utils.randomNum(maux.assets.size());
                aaux = maux.assets.get(rAsset);
                if (aaux.price <= b.getMoney() && aaux.curve10 < 10 && aaux.getBankruptcyIndex() > 1) {
                    decidedOnABadBuy = true; //decided on a bad buy
                }
            }
            if (!decidedOnABadBuy) {
                while (aaux.price > b.getMoney()) {
                    rAsset = Utils.randomNum(maux.assets.size());
                    aaux = maux.assets.get(rAsset);
                }
            }
            int rQuant = Math.max(Utils.randomNum(b.getMoney() / aaux.price), 1); //He buys either 1 or a random amount he can afford
            if (!b.playerBuyAsset(rAsset, rQuant)) {
                throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
            }
            memory.add(new Pair<>(aaux, aaux.getPrice()));
            if (decidedOnABadBuy) {
                System.out.println(b.getName() + " questionably bought " + rQuant + " shares of " + aaux.name + ".");
            } else {
                System.out.println(b.getName() + " out of spite bought " + rQuant + " shares of " + aaux.name + ".");
            }
        }
        else{
            System.out.println(b.getName() + " wants to buy shares but is very short on cash, what a dumbass.");
        }
    }


    @Override
    public void sellAsset(Bot b) {
        Market maux = Market.getInstance();
        int profitableAssetInPortfolio = findProfitableAssetInPortfolio(b);
        if (profitableAssetInPortfolio != -1) { //Found one
            Asset aaux = b.getPortfolio().get(profitableAssetInPortfolio).getKey();
            int rQuant = Math.max(Utils.randomNum(b.getPortfolio().get(profitableAssetInPortfolio).getValue()), 1); //He sells a random amount cause he's a dumbass
            this.memoryRemoveAsset(aaux);
            if (!b.playerSellAsset(profitableAssetInPortfolio, rQuant)) {
                throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
            }
            System.out.println(b.getName() + " sold " + rQuant + " shares of " + aaux.name + " since he saw the opportunity.");
        }
        else {
            System.out.println(b.getName() + " wants to sell but he can't decide on what to.");
        }
    }


    @Override
    public void updateMemory(Bot b) {
        Iterator<Pair<Asset, Integer>> iter = memory.iterator();
        while (iter.hasNext()) {
            Asset a = iter.next().getKey();
            if (a.isBankrupt()){
                iter.remove();
            }
        }
    }


}