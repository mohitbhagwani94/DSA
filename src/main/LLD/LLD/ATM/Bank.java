package LLD.ATM;

public class Bank {
    int balance;
    public void withdraw(int withdraw){
        balance -= withdraw;
    }

    public int getBalance(){
        return balance;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

}
