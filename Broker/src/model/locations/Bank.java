package model.locations;


import model.actions.ReturnLoanAction;
import model.actions.TakeLoanAction;

public class Bank extends Location {

    public Bank() {
        super("Bank");
        addAction(new TakeLoanAction());
        addAction(new ReturnLoanAction());
    }
}