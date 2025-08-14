package LLD.ATM.ATMStates;

import LLD.ATM.ATM;
import LLD.ATM.Card;
import LLD.ATM.OperationTypes;

public abstract class ATMState {

    public void insertCard(ATM atm, Card card){
        System.out.println("Kindly Insert ATM card");
    }

    public void authenticatePin(Card card, ATM atm, String pin){
        System.out.println("OOPS! something went wrong");
    }

    public void selectOperations(Card card, ATM atm, OperationTypes operationTypes){
        System.out.println("OOPS! something went wrong");
    }

    public void withdrawMoney(Card card, ATM atm, int amount) {
        System.out.println("OOPS! something went wrong");
    }

    public void displayBalance(Card card, ATM atm){
        System.out.println("OOPS! something went wrong");
    }

    public void returnCard(){
        System.out.println("Collect ur card");
    }

    public void exit(ATM atm){
        returnCard();
        atm.setCurrentAtmState(new IdleState());
        System.out.println("Exited");
    }

}
