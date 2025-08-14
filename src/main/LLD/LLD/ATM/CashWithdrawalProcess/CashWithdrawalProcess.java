package LLD.ATM.CashWithdrawalProcess;


import LLD.ATM.ATM;

public abstract class CashWithdrawalProcess {
    CashWithdrawalProcess nextCashWithdrawalProcess;
    CashWithdrawalProcess(CashWithdrawalProcess cashWithdrawalProcess){
        nextCashWithdrawalProcess = cashWithdrawalProcess;
    }

    public void withdraw(ATM atm, int remainAmout){
        if(nextCashWithdrawalProcess!= null) {
            nextCashWithdrawalProcess.withdraw(atm, remainAmout);
        }
    }
}
