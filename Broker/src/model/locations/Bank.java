package model.locations;


import model.actions.bankActions.ReturnLoanAction;
import model.actions.bankActions.TakeLoanAction;

public class Bank extends Location {

    public Bank() {
        super("Bank");
        addAction(new TakeLoanAction());
        addAction(new ReturnLoanAction());
    }
}