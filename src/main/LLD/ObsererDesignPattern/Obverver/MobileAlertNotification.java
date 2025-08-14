package ObsererDesignPattern.Obverver;

import ObsererDesignPattern.Obvervable.StockObservable;

public class MobileAlertNotification implements NotificationAlertObserver {
    String mobileNumber;
    StockObservable observable;

    public MobileAlertNotification(String mobileNumber, StockObservable observable){
            this.mobileNumber = mobileNumber;
            this.observable = observable;
    }

    @Override
    public void update() {
        sendMsgOnMobile(mobileNumber,"Product is in stock");
    }
    private void sendMsgOnMobile(String mobileNumber, String msg){
        System.out.println("Sent msg"+msg+" on mobile number"+mobileNumber);
    }
}
