package LLD.ATM.CashWithdrawalProcess;

import LLD.ATM.ATM;

public class Withdraw2000P extends CashWithdrawalProcess{
    public Withdraw2000P(CashWithdrawalProcess cashWithdrawalProcess) {
        super(cashWithdrawalProcess);
    }

    public void withdraw(ATM atm, int remainingAmount ){
        int noteReq = remainingAmount/2000;
        remainingAmount = remainingAmount%2000;

        if(noteReq<atm.getNoOf2000()){
            atm.deduct2000Notes(noteReq);
        }else if(){

        }
    }

}
