package model.players.marketstrategies;

import model.players.Bot;
import model.players.Sex;
import model.trading.Asset;
import model.trading.Market;
import model.utils.Pair;
import model.utils.Utils;

import java.util.ArrayList;

public class greedyStrategy extends MarketCommonKnowledge implements MarketStrategy {

    @Override
    public void buyAsset(Bot b) {
        updateMemory(b);
        if (Market.getInstance().canBuyAnyWith(b.getMoney())) {
            Market maux = Market.getInstance();
            int rAsset = Utils.randomNum(maux.assets.size());
            Asset aaux = maux.assets.get(rAsset);
            boolean decidedOnACheapAsset = false;
            for (int i = 0; i < maux.assets.size() && !decidedOnACheapAsset; i++) {
                rAsset = Utils.randomNum(maux.assets.size());
                aaux = maux.assets.get(rAsset);
                if (aaux.price <= b.getMoney() && aaux.curve10 > 0 && aaux.getBankruptcyIndex() < 2 && aaux.price < maux.getPriceMean()) {
                    decidedOnACheapAsset = true; //decided on a cheap buy
                }
            }
            if (!decidedOnACheapAsset) {
                while (aaux.price > b.getMoney()) {
                    rAsset = Utils.randomNum(maux.assets.size());
                    aaux = maux.assets.get(rAsset);
                }
            }
            int rQuant = Math.max((b.getMoney() / aaux.price) / 2, 1); // buys either 1 or half of what affords
            if (!b.playerBuyAsset(rAsset, rQuant)) {
                throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
            }
            memory.add(new Pair<>(aaux, aaux.getPrice()));
            if (decidedOnACheapAsset) {
                System.out.println(b.getName() + " purchased " + rQuant + " shares of " + aaux.name + " since "+ Sex.subjectPronoun(b.getSex(), false)+" saw them cheap.");
            } else {
                System.out.println(b.getName() + " out of spite bought " + rQuant + " shares of " + aaux.name + ".");
            }
        }
        else{
            System.out.println(b.getName() + " wants to buy shares but is having economic difficulties.");
        }
    }

    @Override
    public void sellAsset(Bot b) {
        updateMemory(b);
        boolean soldSomething = false;
        ArrayList<Pair<Asset,Integer>> memClone = (ArrayList<Pair<Asset, Integer>>) memory.clone();
        while (!memory.isEmpty()) {
            int profitableAssetInPortfolio = findProfitableAssetInPortfolio(b);
            if (profitableAssetInPortfolio != -1) { //Found one
                Asset aaux = b.getPortfolio().get(profitableAssetInPortfolio).getKey();
                int rQuant = b.getPortfolio().get(profitableAssetInPortfolio).getValue(); //He sells all of them
                if (!aaux.isIndustryBoom()) { //He will only keep track of booms and profitability
                    if (!b.playerSellAsset(profitableAssetInPortfolio, rQuant)) {
                        throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
                    }
                    else{
                        memClone.remove(aaux);
                    }
                    soldSomething = true;
                    System.out.println(b.getName() + " sold " + rQuant + " shares of " + aaux.name + " in a jewish fashion.");
                    break;
                }
                memoryRemoveAsset(aaux);
            }
            else{
                break; //no good assets in portfolio, break while loop.
            }
        }
        if (!soldSomething) {
            System.out.println(b.getName() + " wants to sell but "+ Sex.subjectPronoun(b.getSex(), false)+" can't decide on what to.");
        }
        memory = memClone;
    }

    @Override
    public void sellAssetDebt(Bot b) {
        ArrayList<Pair<Asset, Integer>> memClone = (ArrayList<Pair<Asset, Integer>>) memory.clone();
        while (!memory.isEmpty()) {
            int profitableAssetInPortfolio = findProfitableAssetInPortfolio(b);
            if (profitableAssetInPortfolio != -1) { //Found one
                Asset aaux = b.getPortfolio().get(profitableAssetInPortfolio).getKey();
                int rQuant = b.getPortfolio().get(profitableAssetInPortfolio).getValue();
                if (!b.playerSellAsset(profitableAssetInPortfolio, rQuant)) {
                    throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
                }
                else{
                    System.out.println(b.getName() + " sold " + rQuant + " shares of " + aaux.name + " egotistically to escape debt.");
                    memClone.remove(aaux);
                }
                this.memoryRemoveAsset(aaux);
                if (b.getMoney() >= 0) {
                    break;
                }
            } else {
                break;
            }
        }
        this.memory = memClone;
        while (b.getMoney() < 0 && !b.getPortfolio().isEmpty()){
            Asset aaux = b.getPortfolio().get(0).getKey();
            int qtty = b.getPortfolio().get(0).getValue();
            if (!b.playerSellAsset(0, qtty)) {
                throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
            }
            else{
                memoryRemoveAsset(aaux);
                System.out.println(b.getName() + " sold " + qtty + " shares of " + aaux.name + " egotistically to escape debt.");
            }
        }
    }
}
