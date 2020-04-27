package model.trading;

import controller.Game;
import model.events.EventHandler;
import model.events.LoanInstallmentEvent;
import model.life.Time;
import model.players.Player;
import model.utils.Utils;

import java.util.Scanner;

public class Banker {
    private static final int STARTINGLOANAMOUNT = 100;
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
            score += 2;
        }
        if (!p.getPortfolio().isEmpty()) {
            score += score;
        }
        if (p.getMoney() < 500) {
            score++;
        }
        int amount = ((Utils.randomNum(STARTINGLOANAMOUNT * score) + STARTINGLOANAMOUNT) / 100) * 100;
        double rate = Math.max(0.8 - ((double) (Utils.randomNum(score)) / 10), 0.1);
        int days = Utils.randomNum(7) + 7;
        int freq = Utils.randomNum(3) + 1;
        return new Loan(amount, rate, days, freq);
    }

    public void savingLoan(int money, Player p, boolean isUser) {
        int debt = Math.abs(money);
        int days = Utils.randomNum(7) + 7;
        int freq = Utils.randomNum(3) + 1;
        double rate = ((double) (Utils.randomNum(2) + 2)) / 10;
        int amount = Utils.randomNum(50) * 100 / debt;
        if (amount <= debt) {
            amount = (int) (debt * 1.5);
        }
        Loan loan = new Loan(amount, rate, days, freq);
        if (isUser) {
            System.out.println("The bank offers you a loan to save your ass.");
            System.out.println("Said loan is:" + loan);
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
}