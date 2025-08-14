package LLD.ATM;

import LLD.ATM.ATMStates.ATMState;

public class ATM {

    private int atmBalance;
    ATMState currentAtmState;
    private int noOf2000;
    private int noOf500;
    private int noOf100;

    ATM(int balance,ATMState atmState, int noOf2000,int noOf500,int noOf100){
        this.atmBalance = balance;
        this.currentAtmState = atmState;
        this.noOf100 = noOf100;
        this.noOf500 = noOf500;
        this.noOf2000 = noOf2000;
    }

    public void setCurrentAtmState( ATMState atmState){
        currentAtmState = atmState;
    }

    public ATMState getCurrentAtmState() {
        return currentAtmState;
    }

    public void setAtmBalance(int amount){
        atmBalance = amount;
    }

    public int getAtmBalance(){
        return atmBalance;
    }

    public int getNoOf2000(){
        return noOf2000;
    }

    public int getNoOf500(){
        return noOf500;
    }

    public int getNoOf100(){
        return noOf100;
    }

    public void deductATMBalance(int dAmount){
        atmBalance = atmBalance - dAmount;
    }

    public void deduct2000Notes(int number){
        noOf2000 -= number;
    }

    public void deduct500Notes(int number){
        noOf500 -= number;
    }

    public void deduct100Notes(int number){
        noOf100 -= number;
    }
}
