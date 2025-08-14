package ObsererDesignPattern;

import ObsererDesignPattern.Obvervable.IphoneObservableImp;
import ObsererDesignPattern.Obvervable.StockObservable;
import ObsererDesignPattern.Obverver.EmailAlertNotification;
import ObsererDesignPattern.Obverver.MobileAlertNotification;
import ObsererDesignPattern.Obverver.NotificationAlertObserver;

public class Store {
    public static void main(String arg[]){
        int len = arg.length;
        Math.min(len,len);
        StockObservable iphonStockObservable = new IphoneObservableImp();
        NotificationAlertObserver observer1 = new EmailAlertNotification("xyz@gamil.com",iphonStockObservable);
        NotificationAlertObserver observer2 = new EmailAlertNotification("abc@gmail.com",iphonStockObservable);
        NotificationAlertObserver observer3 = new MobileAlertNotification("+912321",iphonStockObservable);

        iphonStockObservable.add(observer1);
        iphonStockObservable.add(observer2);
        iphonStockObservable.add(observer3);

        iphonStockObservable.setData(100);
        iphonStockObservable.setData(0);
        iphonStockObservable.setData(100);
    }
}
