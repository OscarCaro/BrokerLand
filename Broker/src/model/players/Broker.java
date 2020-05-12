package model.players;

import controller.Game;
import model.actions.Action;
import model.actions.ActionParser;
import model.events.BrokerUpdateEvent;
import model.events.EventHandler;
import model.life.Time;
import model.locations.WorldMap;
import model.trading.Asset;
import model.trading.Banker;
import model.trading.Loan;
import model.utils.Pair;
import model.utils.Utils;

import java.util.List;
import java.util.Scanner;

public class Broker extends Player {

    Action previousAction; //so you cannot break the game doing the same thing over and over again
    private Scanner in;

    public Broker() {
        super("you", "", WorldMap.HOMEIDX, 1000);
        in = new Scanner(System.in);
    }

    public void update() {
        Utils.minusWall();
        System.out.println(ownTime + ". You're now at: " + currLoc);
        Utils.minusWall();
        System.out.println(mentalH + " " + hunger + " Money: $" + money);
        Utils.minusWall();
        showPortfolio();
        Utils.minusWall();
        askActions();
    }

    private void askActions() {
        List<Action> actions = currLoc.getActions();
        List<Action> moveActions = currLoc.getMoveActions();

        System.out.println("What do you want to do?");
        printActions(actions, moveActions);
        System.out.println("Select: ");
        String input = in.nextLine();
        Action action = ActionParser.parseAction(input, actions, moveActions);

        while (action == null) {
            System.out.println("Invalid option");
            System.out.println("What do you want to do?");
            input = in.nextLine();
            action = ActionParser.parseAction(input, actions, moveActions);
        }

        if (previousAction == null || !action.equals(previousAction)) {
            Time actionTime = Game.getTimeClone();
            actionTime.addTime(action.getTime());
            EventHandler.getInstance().addEvent(new BrokerUpdateEvent(actionTime, action, this));
            previousAction = action;
        } else {
            Utils.equalsWall();
            System.out.println("You already did that.");
            Utils.equalsWall();
            this.askActions();
        }
    }

    @Override
    public void reactToGreeting(Player other, String message) {
        System.out.println("What do you respond back: ");
        System.out.println(in.nextLine());
    }

    @Override
    public String getMessageToSay() {
        System.out.println("What do you want to say: ");
        return in.nextLine();
    }

    @Override
    public Player choosePlayerToGreet(List<Player> players) {
        System.out.println("Who do you want to talk to:");
        for (int i = 0; i < players.size(); i++) {
            System.out.println(i + ". " + players.get(i).getName());
        }
        System.out.println("Select: ");
        String aux = in.nextLine();
        while (Integer.parseInt(aux) < 0 || Integer.parseInt(aux) >= players.size()) {
            System.out.println("Invalid option");
            System.out.println("Select: ");
            aux = in.nextLine();
        }
        return players.get(Integer.parseInt(aux));
    }

    public String endMessage() {
        String aux = null;
        if (money < 0) {
            aux = "You went bankrupt and fled the country,\n you're now starting a new life in the fields of Maine.\n";
            aux += "\n-------------- You are done ---------------";
        } else if (mentalH.insane()) {
            aux = "The life you chose has seemed unbearable for a time now,\n" +
                    "and after considering suicide several times too many you decide to get help.\n" +
                    "The first thing you do to change your life around is give up trading.\n ";
            aux += "\n-------------- You are done ---------------";
        } else if (hunger.starved()) {
            aux = "You were so focused on your work and social life\n" +
                    "you have totally forgotten about your own physical health.\n" +
                    "You were hospitalized and the doctors say you'll have to be there\n " +
                    "some months. The first thing they dictate you to do is give up trading.\n";
            aux += "\n-------------- You are done ---------------";
        } else {
            aux = "You are the only broker left in the platform.\n" +
                    "With enormous influence over it and a moneymaking machine in your hands now,\n" +
                    "you start paying attention to the equally enormous void in your heart.\n " +
                    "You are now all alone in a trading world devoid of interest.\n " +
                    "You won, but at what cost.\n";
            aux += "\n-------------- You won ---------------";
        }

        return aux;
    }

    @Override
    public void buy() {
        globalMarket.print();
        System.out.println("You're sure you want to buy one of them? (y/n)");
        String input = in.nextLine();
        if (input.toUpperCase().equals("Y")) {
            int idx = -1;
            while (idx < 0 || idx > globalMarket.getNumOfAssets()) {
                System.out.println("Which one? (0->" + (globalMarket.getNumOfAssets() - 1) + ")");
                input = in.nextLine();
                idx = Integer.parseInt(input);
            }
            System.out.println("How many? (1->" + (this.money / globalMarket.assets.get(idx).price) + ")");
            input = in.nextLine();
            int qtty = Integer.parseInt(input);
            if (!playerBuyAsset(idx, qtty)) {
                System.out.println("You cannot buy that asset for that quantity.");
            }
        }
    }

    @Override
    public void sell() {
        showPortfolio();
        if (portfolio.size() > 0) {
            System.out.println("Are you sure you want to sell one of them? (y/n)");
            String input = in.nextLine();
            if (input.toUpperCase().equals("Y")) {
                System.out.println("Which one? (index)");
                input = in.nextLine();
                int idx = Integer.parseInt(input);
                while (idx < 0 || idx >= portfolio.size()) {
                    System.out.println("Invalid option. Which one? (index)");
                    input = in.nextLine();
                    idx = Integer.parseInt(input);
                }
                System.out.println("How many? (quantity)");
                input = in.nextLine();
                int qtty = Integer.parseInt(input);
                while (qtty < 0 && qtty > portfolio.get(idx).getValue()) {
                    System.out.println("Invalid option. How many? (quantity)");
                    input = in.nextLine();
                    qtty = Integer.parseInt(input);
                }
                this.playerSellAsset(idx, qtty);
            }
        }
    }

    @Override
    public boolean takeLoan() {
        if (this.loan == null) {
            Loan aux = Banker.getInstance().calculateLoan(this);
            System.out.println("You have been offfered the following loan:");
            System.out.println(aux.loanStatement());
            System.out.println("Will you take it? (y/n)");
            String input = in.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                this.loan = aux;
                Banker.getInstance().giveLoan(loan, this, true);
                System.out.println("You took the loan:");
            } else {
                System.out.println("You did not take the loan:");
            }
            return true;
        } else {
            System.out.println("The banker will not give you another loan until you have paid the last one.");
            return false;
        }
    }

    @Override
    public boolean payBackLoan() {
        if (this.loan.getAmountRemaining() >= this.getMoney()) {
            System.out.println("You cannot pay back your loan because its remaining amount is: $ " + this.loan.getAmountRemaining() + ".");
            System.out.println("You're $ " + (this.loan.getAmountRemaining() - this.getMoney()) + " short.");
            return false;
        } else {
            System.out.println("You have to pay: $ " + this.loan.getAmountRemaining() + ".");
            System.out.println("It will leave you at $ " + (this.getMoney() - this.loan.getAmountRemaining()) + ".");
            System.out.println("Will you pay it? (y/n)");
            String input = in.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                this.payLoanAmount(this.loan.getAmountRemaining());
                loan = null;
                return true;
            } else {
                return false;
            }
        }
    }


    private void showPortfolio() {
        if (portfolio.size() == 0) {
            System.out.println("You currently do not own any assets");
        } else {
            System.out.println("Your assets on the market sit currently at: ");
            int i = 0;
            for (Pair<Asset, Integer> p : portfolio) {
                Utils.minusWall();
                System.out.println(i + ": " + p.getKey().name + "| Selling at: " + p.getKey().price + "| Owned " + p.getValue() + " of them.");
                i++;
            }
            Utils.minusWall();
            System.out.println("Your portfolio now amounts to: " + "$" + money);
        }
    }


    public void printActions(List<Action> actions, List<Action> moveActions) {
        Utils.equalsWall();
        for (Action a : actions) {
            System.out.println(a);
        }
        Utils.minusWall();
        for (Action a : moveActions) {
            System.out.println(a);
        }
        Utils.equalsWall();
    }
}
