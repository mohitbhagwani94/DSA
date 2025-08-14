package LLD.ATM.ATMStates;

import LLD.ATM.ATM;
import LLD.ATM.Card;
import LLD.ATM.OperationTypes;

public class OperationSelectionState extends ATMState{
    OperationSelectionState(){
        OperationTypes.showAllOperationTypes();
    }

    @Override
    public void selectOperations(Card card, ATM atm, OperationTypes operationTypes) {
        switch (operationTypes){
            case BALANCE_CHECK:
                atm.setCurrentAtmState(new CheckBalanceState());
                break;
            case CASH_WITHDRAWAL:
                atm.setCurrentAtmState(new CashWithdrawlState());
                break;
            default: {
                System.out.println("Invalid option");
                exit(atm);
            }
        }
    }
}
