package LLD.ATM.ATMStates;

import LLD.ATM.ATM;
import LLD.ATM.Card;

public class HasCardState extends ATMState{

    HasCardState(){
        System.out.println("Enter your card pin");
    }

    @Override
    public void authenticatePin(Card card, ATM atm, String pin) {
        if(card.isEnteredPinCorrect(pin)){
            atm.setCurrentAtmState(new OperationSelectionState());
            System.out.println("Pin Authenticated");
        }else{
            System.out.println("Incorrect pin");
            exit(atm);
        }
    }
}
