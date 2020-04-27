package model.trading;

import controller.Game;
import model.life.Time;

public class Loan {
    int daysToTimeEnd;
    int installment;
    private int amount;
    private double rate;
    private int amountRemaining;
    private int daysFreq;
    private Time timeEnd;
    private int numInstallments;
    public Loan(int amount, double rate, int daysToTimeEnd, int daysFreq) {
        this.amount = amount;
        this.rate = rate;
        timeEnd = Game.getTimeClone();
        timeEnd.addTime(daysToTimeEnd * 60 * 24);
        this.daysToTimeEnd = daysToTimeEnd;
        this.daysFreq = daysFreq;
        numInstallments = (daysToTimeEnd / daysFreq);
        this.installment = (int) (amount * (1+rate)) / numInstallments;
        this.amountRemaining = (int) (amount * (1+rate));
    }

    public void pay(int money) {
        amountRemaining -= money;
    }

    public int getAmountRemaining() {
        return amountRemaining;
    }

    public int getInstallment() {
        return installment;
    }

    public String loanStatement() {
        return "Amount: $ " + this.amount + ". Rate (non cumulative): " + this.rate  + ". Total amount: $ "+ this.amountRemaining + ". Frequency (Days): " + daysFreq + ". Days to pay off: " + daysToTimeEnd;
    }

    public int getDaysToTimeEnd() {
        return daysToTimeEnd;
    }

    public int getDaysFreq() {
        return daysFreq;
    }

    public double getRate() {
        return rate;
    }

    public int getAmount() {
        return amount;
    }
}
