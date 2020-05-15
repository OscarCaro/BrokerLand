package model.players.marketstrategies;

import model.players.Bot;
import model.players.Sex;
import model.trading.Asset;
import model.trading.Market;
import model.utils.Pair;
import model.utils.Utils;

import java.util.ArrayList;

public class aggressiveStrategy extends MarketCommonKnowledge implements MarketStrategy {

    @Override
    public void buyAsset(Bot b) {
        updateMemory(b);
        if (Market.getInstance().canBuyAnyWith(b.getMoney())) {
            Market maux = Market.getInstance();
            int rAsset = Utils.randomNum(maux.assets.size());
            Asset aaux = maux.assets.get(rAsset);
            boolean decidedOnAnUnderperformingAsset = false;
            for (int i = 0; i < maux.assets.size() && !decidedOnAnUnderperformingAsset; i++) {
                rAsset = Utils.randomNum(maux.assets.size());
                aaux = maux.assets.get(rAsset);
                if (aaux.price <= b.getMoney() && aaux.curve10 > 0 && aaux.getBankruptcyIndex() < 2) {
                    decidedOnAnUnderperformingAsset = true; //decided on a bad buy
                }
            }
            if (!decidedOnAnUnderperformingAsset) {
                while (aaux.price > b.getMoney()) {
                    rAsset = Utils.randomNum(maux.assets.size());
                    aaux = maux.assets.get(rAsset);
                }
            }
            int rQuant = Math.max(Utils.randomNum(b.getMoney() / aaux.price), 1); //buys either 1 or a random amount that can afford
            if (!b.playerBuyAsset(rAsset, rQuant)) {
                throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
            }
            memory.add(new Pair<>(aaux, aaux.getPrice()));
            if (decidedOnAnUnderperformingAsset) {
                System.out.println(b.getName() + " aggressively purchased " + rQuant + " shares of " + aaux.name + ".");
            } else {
                System.out.println(b.getName() + " out of spite bought " + rQuant + " shares of " + aaux.name + ".");
            }
        }
        else{
            System.out.println(b.getName() + " wants to buy shares but is very short on cash, "+ Sex.subjectPronoun(b.getSex(), false)+" is infuriated.");
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
                int rQuant = b.getPortfolio().get(profitableAssetInPortfolio).getValue(); // sells all of them
                if (!aaux.isIndustryCrash() || aaux.isIndustryBoom() || aaux.curve10 < 0) {
                    if (!b.playerSellAsset(profitableAssetInPortfolio, rQuant)) {
                        throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
                    }
                    soldSomething = true;
                    System.out.println(b.getName() + " sold " + rQuant + " shares of " + aaux.name + " avidly.");
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
                    System.out.println(b.getName() + " sold " + rQuant + " shares of " + aaux.name + " aggressively to escape debt.");
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
                System.out.println(b.getName() + " sold " + qtty + " shares of " + aaux.name + " aggressively to escape debt.");
            }
        }
    }

}
