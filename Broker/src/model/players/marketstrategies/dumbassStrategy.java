package model.players.marketstrategies;

import model.players.Bot;
import model.trading.Asset;
import model.trading.Market;
import model.utils.Pair;
import model.utils.Utils;

import java.util.ArrayList;

public class dumbassStrategy implements MarketStrategy {
    private ArrayList<Pair<Asset, Integer>> memory = new ArrayList<>();

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
            int rQuant = b.getPortfolio().get(profitableAssetInPortfolio).getValue(); //He sells all of them
            if (!b.playerSellAsset(profitableAssetInPortfolio, rQuant)) {
                throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
            }
            System.out.println(b.getName() + " sold " + rQuant + " shares of " + aaux.name + " since he saw the opportunity.");
        }
        else {
            System.out.println(b.getName() + " wants to sell but he can't decide on what to.");
        }
    }

    private int findProfitableAssetInPortfolio(Bot b) {
        for (Pair<Asset, Integer> p : memory) {
            if (p.getKey().price > p.getValue()) {
                for (Pair<Asset, Integer> p2 : b.getPortfolio()) {
                    if (p.getKey().equals(p2.getKey())) {
                        return b.getPortfolio().indexOf(p2);
                    }
                }
            }
        }
        return -1;
    }
}