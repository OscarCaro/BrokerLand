package model.locations;


import model.actions.bankActions.ReturnLoanAction;
import model.actions.bankActions.SitBySculptureAction;
import model.actions.bankActions.TakeLoanAction;
import model.actions.moveActions.*;

public class Bank extends Location {

    public Bank() {
        super("Bank");
        addAction(new TakeLoanAction());
        addAction(new ReturnLoanAction());
        addAction(new SitBySculptureAction());
        addMoveAction(new goHomeAction());
        addMoveAction(new goOfficeAction());
        addMoveAction(new goParkAction());
        addMoveAction(new goBarAction());
    }
}