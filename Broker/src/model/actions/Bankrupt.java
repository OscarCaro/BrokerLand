package model.actions;

import model.players.Player;
import model.trading.Asset;
import model.trading.Market;

public class Bankrupt extends Action {

    public Bankrupt() {
        super("b", "b", "Bankrupt everything.", 60, -5);
    }

    @Override
    protected void performSpecificAction(Player player, boolean isUser) {
        if (isUser) {
            System.out.println("You killed the market.");
            for (Asset a : Market.getInstance().assets) {
                a.setBankrupt();
            }
        } else {
            System.out.println(player.getName() + " tried to kill the market what a bot.");
        }
    }

}
