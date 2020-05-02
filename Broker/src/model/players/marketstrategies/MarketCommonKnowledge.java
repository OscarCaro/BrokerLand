package model.players.marketstrategies;

import model.players.Bot;
import model.trading.Asset;
import model.utils.Pair;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class MarketCommonKnowledge implements  MarketStrategy{
    protected ArrayList<Pair<Asset, Integer>> memory = new ArrayList<>();

    protected void memoryRemoveAsset(Asset a) {
        Iterator<Pair<Asset, Integer>> iter = memory.iterator();
        while (iter.hasNext()) {
            Asset aux = iter.next().getKey();
            if (aux.equals(a)){
                iter.remove();
                break;
            }
        }
    }

    protected int findProfitableAssetInPortfolio(Bot b) {
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
