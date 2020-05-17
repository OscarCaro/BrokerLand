package tests;

import model.trading.Asset;
import model.trading.assetStates.AssetState;
import org.junit.jupiter.api.Test;

class AssetTest extends TestCommons {

    @Test
    void refreshAsset() {
        initGameAndMarket();
        Asset a = new Asset();
        boolean aux = true;
        AssetState s1;
        for (int i = 0; i < 10 && aux; i++) { //10 refreshes with volatility 0.9 quasi-guarantees change of asset state
            s1 = a.getState();
            a.refreshAsset(0.9);
            if (!s1.equals(a.getState())){
                aux = false;
            }
        }
        assertFalse(aux);
    }
}