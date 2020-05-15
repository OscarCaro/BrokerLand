package model.players.marketstrategies;

import model.players.Bot;
import model.players.Sex;
import model.trading.Asset;
import model.trading.Market;
import model.utils.Pair;
import model.utils.Utils;

import java.util.ArrayList;

public class knowledgeableStrategy extends MarketCommonKnowledge implements MarketStrategy {

    @Override
    public void buyAsset(Bot b) {
        updateMemory(b);
        if (Market.getInstance().canBuyAnyWith(b.getMoney())) {
            Market maux = Market.getInstance();
            int rAsset = Utils.randomNum(maux.assets.size());
            Asset aaux = maux.assets.get(rAsset);
            boolean decidedOnAGoodAsset = false;
            for (int i = 0; i < maux.assets.size() && !decidedOnAGoodAsset; i++) {
                rAsset = Utils.randomNum(maux.assets.size());
                aaux = maux.assets.get(rAsset);
                if ((aaux.price <= b.getMoney() && aaux.curve10 > 1 && aaux.getBankruptcyIndex() < 2) || (aaux.price <= b.getMoney() && aaux.isIndustryBoom())) {
                    decidedOnAGoodAsset = true; //decided on a nice buy
                }
            }
            if (!decidedOnAGoodAsset) {
                while (aaux.price > b.getMoney()) {
                    rAsset = Utils.randomNum(maux.assets.size());
                    aaux = maux.assets.get(rAsset);
                }
            }
            int rQuant = Math.max(Utils.randomNum(b.getMoney() / aaux.price / 2), 1); //He buys either 1 or a random but prudent number
            if (!b.playerBuyAsset(rAsset, rQuant)) {
                throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
            }
            memory.add(new Pair<>(aaux, aaux.getPrice()));
            if (decidedOnAGoodAsset) {
                System.out.println(b.getName() + " wisely acquired " + rQuant + " shares of " + aaux.name + ".");
            } else {
                System.out.println(b.getName() + " out of spite bought " + rQuant + " shares of " + aaux.name + ".");
            }
        } else {
            System.out.println(b.getName() + " wants to buy shares but has to put " + Sex.objectPronoun(b.getSex()) + " matters in order before " + Sex.subjectPronoun(b.getSex(), false) + " can.");
        }
    }

    @Override
    public void sellAsset(Bot b) {
        updateMemory(b);
        boolean soldSomething = false;
        ArrayList<Pair<Asset, Integer>> memClone = (ArrayList<Pair<Asset, Integer>>) memory.clone();
        while (!memory.isEmpty()) {
            int profitableAssetInPortfolio = findProfitableAssetInPortfolio(b);
            if (profitableAssetInPortfolio != -1) { //Found one
                Asset aaux = b.getPortfolio().get(profitableAssetInPortfolio).getKey();
                int rQuant = b.getPortfolio().get(profitableAssetInPortfolio).getValue(); //He sells all of them
                if (!aaux.isIndustryBoom() && aaux.curve10 <= 0) { //He will keep track of booms profitability and performance
                    if (!b.playerSellAsset(profitableAssetInPortfolio, rQuant)) {
                        throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
                    }
                    else{
                        memClone.remove(aaux);
                    }
                    soldSomething = true;
                    System.out.println(b.getName() + " sold " + rQuant + " shares of " + aaux.name + " avidly.");
                    break;
                }
                memoryRemoveAsset(aaux);
            } else {
                break; //no good assets in portfolio, break while loop.
            }
        }
        if (!soldSomething) {
            System.out.println(b.getName() + " wants to sell but " + Sex.subjectPronoun(b.getSex(), false) + " can't decide on what to.");
        }
        this.memory = memClone;
    }

    @Override
    public void sellAssetDebt(Bot b) {
        int i = 0;
        for (Pair<Asset, Integer> p : b.getPortfolio()) {
            if (p.getKey().isIndustryCrash()) {
                Asset aaux = p.getKey();
                int qtty = p.getValue();
                if (!b.playerSellAsset(i, qtty)) {
                    throw new IllegalArgumentException("Bot " + this + " cannot make such a transaction.");
                }
                else{
                    System.out.println(b.getName() + " sold " + qtty + " shares of " + aaux.name + " avidly to escape debt.");
                    memoryRemoveAsset(aaux);
                    break; //it will only sell at most one crashing asset intentionally.
                }
            }
            i++;
        }
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
                    System.out.println(b.getName() + " sold " + rQuant + " shares of " + aaux.name + " avidly to escape debt.");
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
                System.out.println(b.getName() + " sold " + qtty + " shares of " + aaux.name + " avidly to escape debt.");
            }
        }
    }
}
