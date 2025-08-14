package LLD.ATM.ATMStates;

import LLD.ATM.ATM;
import LLD.ATM.Card;

public class CheckBalanceState extends ATMState{
    @Override
    public void displayBalance(Card card, ATM atm) {
        System.out.println(card.getCardBalance());
        exit(atm);
    }
}
