package model.trading;

import model.players.Player;
import model.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//This class follows the Singleton pattern, since there should be only one Market
public class Market {

    private static final int _marketGlobalBound = 30;
    private static Market instance;
    public List<Asset> assets;
    private int startingAssetsNum;
    private int minimumAssetsNum;
    private int priceMean;
    private double volatility;

    private Market() {
        assets = new ArrayList<>();
    }

    public static Market getInstance() {
        if (instance == null) {
            instance = new Market();
        }
        return instance;
    }

    public void changeMinimumAssetsNum(int minimumAssetsNum) {
        this.minimumAssetsNum = minimumAssetsNum;
    }

    public void initMarket(int startingAssetsNum, int minimumAssetsNum, double volatility) {
        this.changeMinimumAssetsNum(minimumAssetsNum);
        this.initAssets(startingAssetsNum);
        this.setVolatility(volatility);
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public void initAssets(int startingAssetsNum) {
        this.startingAssetsNum = startingAssetsNum;
        for (int i = 0; i < Math.max(this.startingAssetsNum, this.minimumAssetsNum); i++) {
            this.addNewAsset();
        }
        refreshPriceMean();
    }

    private void addNewAsset() {
        Asset a = null;
        boolean aux = true;
        while (aux) {
            aux = false;
            a = new Asset();
            for (Asset x : assets) {
                if (a.name.equalsIgnoreCase(x.name)) {
                    aux = true;
                }
            }
        }
        assets.add(a);
    }

    public void refresh() {
        while (assets.size() < minimumAssetsNum) {
            this.addNewAsset();
        }
        for (Asset a : assets) {
            a.refreshAsset(volatility);
        }
        if (Utils.randomNum(100) < 100 * (volatility / 2) && assets.size() < _marketGlobalBound) {
            this.addNewAsset();
        }
        refreshPriceMean();
    }

    private void refreshPriceMean() {
        priceMean = 0;
        for (Asset a : assets) {
            priceMean += a.price;
        }
        priceMean /= assets.size();
    }


    public void opportunity() {
        if (assets.size() < _marketGlobalBound)
            this.addNewAsset();
    }

    public void print() {
        System.out.println("The market sits currently at: ");
        for (int i = 0; i < assets.size(); i++) {
            System.out.println(i + ": " + assets.get(i));
        }
    }

    public boolean buy(Player player, int assetIdx, int quantity) {
        if (assetIdx >= 0 && assetIdx < assets.size()) {
            return assets.get(assetIdx).buy(player, quantity);
        }
        return false;
    }

    public boolean sell(Player player, int assetIdx, int quantity) {
        if (assetIdx >= 0 && assetIdx < assets.size() && quantity <= assets.get(assetIdx).sharesOwned) {
            return assets.get(assetIdx).sell(player, quantity);
        }
        return false;
    }

    public int getNumOfAssets() {
        return assets.size();
    }

    public int marketIndex(Asset key) {
        for (int i = 0; i < assets.size(); i++) {
            if (key.equals(assets.get(i))) return i;
        }
        return -1;
    }

    public boolean canBuyAnyWith(int money) {
        for (Asset a : assets) {
            if (a.price <= money)
                return true;
        }
        return false;
    }

    public Asset bankruptAsset() {
        Iterator<Asset> iter = assets.iterator();
        while (iter.hasNext()) {
            Asset a = iter.next();
            if (a.isBankrupt()) {
                System.out.println("-----------------" + a.name + " declared bankruptcy!-----------------");
                return a;
            }
        }
        return null;
    }

    public int getPriceMean() {
        return priceMean;
    }

}
