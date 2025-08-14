package ObsererDesignPattern.Obverver;

import ObsererDesignPattern.Obvervable.StockObservable;

public class EmailAlertNotification implements NotificationAlertObserver {
    String email;
    StockObservable observable;

    public EmailAlertNotification(String email,StockObservable observable){
        this.email =email;
        this.observable = observable;
    }


    @Override
    public void update() {
        sendEmail(email,"Product is in stock");
    }

    private void sendEmail(String emailId, String msg){
        System.out.println("Email sent to:"+emailId+" with msg:"+msg);
    }
}
