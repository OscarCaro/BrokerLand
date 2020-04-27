package model.events;

import model.life.Time;
import model.players.Player;

public class LoanInstallmentEvent extends Event {
    private Player p;
    private boolean isUser;
    private int installmentAmount;
    public LoanInstallmentEvent(Time triggerTime, int installmentAmount, Player p,boolean isUser) {
        super(triggerTime);
        stopHere = false;
        this.p = p;
        this.installmentAmount = installmentAmount;
        this.isUser = isUser;
    }

    @Override
    public void execute() {
        p.payInstallment(isUser, installmentAmount);
    }
}
