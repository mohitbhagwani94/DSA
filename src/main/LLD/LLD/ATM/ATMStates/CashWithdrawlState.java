package LLD.ATM.ATMStates;

import LLD.ATM.ATM;
import LLD.ATM.Card;
import LLD.ATM.CashWithdrawalProcess.CashWithdrawalProcess;
import LLD.ATM.CashWithdrawalProcess.Withdraw100P;
import LLD.ATM.CashWithdrawalProcess.Withdraw2000P;
import LLD.ATM.CashWithdrawalProcess.Withdraw500P;

public class CashWithdrawlState extends ATMState {
    @Override
    public void withdrawMoney(Card card, ATM atm, int debitAmount) {
        if(atm.getAtmBalance()<debitAmount){
            System.out.println("ATM ran out of money");
            exit(atm);
        }else if(card.getCardBalance()<debitAmount){
            System.out.println("Account doesn't have enough Money");
            exit(atm);
        }else{
            CashWithdrawalProcess cwp = new Withdraw2000P( new Withdraw500P(new Withdraw100P(null)));
            cwp.withdraw(atm,debitAmount);

        }
    }
}
