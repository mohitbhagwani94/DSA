package LLD.ATM;

import LLD.ATM.ATMStates.IdleState;

public class ATMRoom {
    ATM atm;
    User user;

    public static void main(String arg[]){
        ATMRoom atmRoom = new ATMRoom();
        atmRoom.initialize();
        atmRoom.atm.getCurrentAtmState();
        atmRoom.atm.getCurrentAtmState().insertCard(atmRoom.atm,atmRoom.user.card);
        atmRoom.atm.getCurrentAtmState().authenticatePin(atmRoom.user.card,atmRoom.atm,"12345");
        atmRoom.atm.getCurrentAtmState().selectOperations(atmRoom.user.card, atmRoom.atm, OperationTypes.BALANCE_CHECK);
        atmRoom.atm.getCurrentAtmState().displayBalance(atmRoom.user.card,atmRoom.atm);

        atmRoom.atm.getCurrentAtmState().insertCard(atmRoom.atm,atmRoom.user.card);
        atmRoom.atm.getCurrentAtmState().authenticatePin(atmRoom.user.card,atmRoom.atm,"12345");
        atmRoom.atm.getCurrentAtmState().selectOperations(atmRoom.user.card, atmRoom.atm, OperationTypes.CASH_WITHDRAWAL);
        atmRoom.atm.getCurrentAtmState().withdrawMoney(atmRoom.user.card, atmRoom.atm, 1000);

    }

    public void initialize(){
        atm = new ATM(300, new IdleState(),3,3,5);
        createUser();
    }

    public void createUser(){
        Card card = new Card( 4000);
        user = new User("Mohit",card);
    }
}
