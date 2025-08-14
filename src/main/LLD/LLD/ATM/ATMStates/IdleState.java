package LLD.ATM.ATMStates;

import LLD.ATM.ATM;
import LLD.ATM.Card;

public class IdleState extends ATMState {
    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Kindly insert ur card");
        atm.setCurrentAtmState(new HasCardState());
    }
}
