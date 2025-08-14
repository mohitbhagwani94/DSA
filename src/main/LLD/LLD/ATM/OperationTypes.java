package LLD.ATM;

public enum OperationTypes {
    CASH_WITHDRAWAL("Cash Withdrawal"),
    BALANCE_CHECK("Check Balance");

    private final String description;

    OperationTypes(String description) {
        this.description = description;
    }

    public static void showAllOperationTypes(){
        for(OperationTypes op: OperationTypes.values()){
            System.out.println(op.name() +"-"+op.description);
        }
    }
}
