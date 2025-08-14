package LLD.ATM;

import java.math.BigInteger;
import java.util.Date;

public class Card {
    int cvv;
    Date expiryDate;
    BigInteger cardNumber;
    String pin = "12345";

    Bank bankAccount;

    Card(){
        bankAccount = new Bank();
    }
    Card(int balance){
       bankAccount = new Bank();
       bankAccount.setBalance(balance);
    }

    public boolean isEnteredPinCorrect(String enteredPin){
        if(enteredPin.equals(pin)){
            return true;
        }
        return false;
    }

    public int getCardBalance(){
        return bankAccount.getBalance();
    }

    public void setCardBalance(int balance){
        bankAccount.setBalance(balance);
    }

    public void deductAmount(int balance){
        bankAccount.withdraw(balance);
    }
}
