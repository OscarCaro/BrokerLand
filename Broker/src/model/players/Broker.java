package model.players;

import model.actions.Action;
import model.actions.ActionParser;
import model.locations.WorldMap;
import model.trading.Asset;
import model.trading.Banker;
import model.trading.Loan;
import model.utils.Pair;
import model.utils.Utils;

import java.util.List;
import java.util.Scanner;

public class Broker extends Player {

    private Scanner in;

    public Broker() {
        super("You", "", WorldMap.HOMEIDX, 1000);
        in = new Scanner(System.in);
    }

    public void update() {
        Utils.minusWall();
        System.out.println(ownTime + ". You're now at: " + currLoc);
        Utils.minusWall();
        System.out.println(mentalH + " " + hunger + " Money:" + money);
        Utils.minusWall();
        showPortfolio();
        Utils.minusWall();
        System.out.println();
        askActions();
    }

    private void askActions() {
        List<Action> actions = currLoc.getActions();    // Call returns an unmodifiable list
        System.out.println("What do you want to do?");
        printActions(actions);
        System.out.println("Select: ");
        String aux = in.nextLine();
        while (!ActionParser.parseAction(aux, actions, this, true)) {
            System.out.println("Invalid option");
            System.out.println("What do you want to do?");
            aux = in.nextLine();
        }
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
        System.out.println("Do you want to buy any of them? (y/n)");
        String input = in.nextLine();
        while (input.toUpperCase().equals("Y")) {
            mentalH.add(-10);
            System.out.println("Which one? (0->" + (globalMarket.getNumOfAssets() - 1) + ")");
            input = in.nextLine();
            int idx = Integer.parseInt(input);
            System.out.println("How many? (1->" + (this.money / globalMarket.assets.get(idx).price) + ")");
            input = in.nextLine();
            int qtty = Integer.parseInt(input);
            if (!playerBuyAsset(idx, qtty)) {
                System.out.println("You cannot buy that asset");
            }
            showPortfolio();
            System.out.println("Do you want to buy any more assets? (y/n)");
            input = in.nextLine();
            if (input.toUpperCase().equals("Y")) {
                globalMarket.print();
            }

        }
    }

    @Override
    public void sell() {
        showPortfolio();
        if (portfolio.size() > 0) {
            System.out.println("Do you want to sell any? (y/n)");
            String input = in.nextLine();
            while (!input.toUpperCase().equals("N")) {
                mentalH.add(-10);
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
                showPortfolio();
                if (portfolio.size() > 0) {
                    System.out.println("Do you want to sell any others? (y/n)");
                    input = in.nextLine();
                } else {
                    input = "N"; //it HAS to break, but i dont want to insert break chain.
                }
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
        if (this.loan.getAmountRemaining() >= this.getMoney()){
            System.out.println("You cannot pay back your loan because its remaining amount is: $ " + this.loan.getAmountRemaining() + ".");
            System.out.println("You're $ " + (this.loan.getAmountRemaining() - this.getMoney()) + " short.");
            return false;
        }
        else{
            System.out.println("You have to pay: $ " + this.loan.getAmountRemaining() + ".");
            System.out.println("It will leave you at $ " + (this.getMoney() - this.loan.getAmountRemaining()) + ".");
            System.out.println("Will you pay it? (y/n)");
            String input = in.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                this.payLoanAmount(this.loan.getAmountRemaining());
                loan = null;
                return true;
            }
            else{
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

            System.out.println("\nYour portfolio now amounts to: " + "$" + money);
        }
    }


    public void printActions(List<Action> actions) {
        Utils.equalsWall();
        for (Action a : actions) {
            System.out.println(a);
        }
        Utils.equalsWall();
    }
}
