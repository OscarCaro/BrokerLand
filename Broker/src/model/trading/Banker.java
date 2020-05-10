package model.trading;

import controller.Game;
import model.events.EventHandler;
import model.events.LoanInstallmentEvent;
import model.life.Time;
import model.players.Player;
import model.utils.Utils;

import java.util.Scanner;

public class Banker {
    private static final int STARTINGLOANAMOUNT = 300;
    private static Banker instance;
    int loansGiven;

    private Banker() {
        loansGiven = 0;
    }

    public static Banker getInstance() {
        if (instance == null) {
            instance = new Banker();
        }
        return instance;
    }

    public void giveLoan(Loan l, Player p, boolean isUser) {
        for (int i = 0; i < l.getDaysToTimeEnd() / l.getDaysFreq(); i++) {
            Time tAux = Game.getTimeClone();
            tAux.addTime(i * l.getDaysFreq() * 60 * 24);
            EventHandler.getInstance().addEvent(new LoanInstallmentEvent(tAux, l.getInstallment(), p, isUser));
        }
        p.modifyMoney(l.getAmount());
    }

    public Loan calculateLoan(Player p) {
        int score = 1;
        if (p.getMoney() > 1000) {
            score += 2;
        }
        if (p.getMoney() > 2000) {
            score += 3;
        }
        if (!p.getPortfolio().isEmpty()) {
            score += 2;
        }
        else if(p.getPortfolio().size() > 1){
            score += 3;
        }
        if (p.getMoney() < 500) {
            score++;
        }
        if (Utils.randomNum(10)>5){
            score++;
        }
        int amount = ((Utils.randomNum(STARTINGLOANAMOUNT * score) + STARTINGLOANAMOUNT) / 100) * 100;
        double rate = Math.max(0.6 - ((double) (Utils.randomNum(score)) / 10), 0.1);
        int days = Utils.randomNum(12) + 7;
        int freq = Utils.randomNum(4) + 1;
        return new Loan(amount, rate, days, freq);
    }

    public void savingLoan(int debt, Player p, boolean isUser) {
        Loan loan = calculateSavingLoan(debt);
        if (isUser) {
            System.out.println("The bank offers you a loan to save your ass.");
            System.out.println("Said loan is:" + loan.loanStatement());
            System.out.println("Will you take it? (Y/N)");
            Scanner aux = new Scanner(System.in);
            String input = aux.nextLine();
            if (input.toUpperCase().equals("Y")) {
                System.out.println("You took the loan.");
                p.takeSavingLoan(loan);
                Banker.getInstance().giveLoan(loan, p, true);
            } else {
                System.out.println("You did not take the loan.");
            }
        } else {
            p.takeSavingLoan(loan);
            Banker.getInstance().giveLoan(loan, p, false);
            System.out.println(p.getName() + " took a rescuing loan to save himself from debt.");
        }
    }

    private Loan calculateSavingLoan(int debt){
        int money = Math.abs(debt);
        int days = Utils.randomNum(12) + 7;
        int freq = Utils.randomNum(4) + 1;
        int amount = 0;
        double rate = ((double) (Utils.randomNum(2) + 2)) / 10;
        if  (money <= 300) { //not a substantial debt so it won't scale upwards too much
            amount += Utils.randomNum(50 * money) * 100;
            if (amount <= money) {
                amount = ((money * Math.max(Utils.randomNum(13), 5) * 100) / 100);
            }
        }
        else{
            amount += Utils.randomNum(50 * money) * 100;
            if (amount <= money) {
                amount = ((money * Math.max(Utils.randomNum(7), 5) * 100) / 100);
            }
            amount = Math.min(12000, amount); //amount is bounded at 12000 just in case it scaled too much
        }
        return new Loan(amount, rate, days, freq);
    }
}