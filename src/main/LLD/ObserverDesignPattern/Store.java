package Practice.ObserverDesignPattern;

import Practice.ObserverDesignPattern.Observable.IphoneStockObservable;
import Practice.ObserverDesignPattern.Observer.EmailAlertObserver;
import Practice.ObserverDesignPattern.Observer.MobileAlertObserver;
import Practice.ObserverDesignPattern.Observer.NotificationObserver;

public class Store {
    public static void main(String[] args) {
        args[1].equals("string");

        IphoneStockObservable iphoneStockObservable = new IphoneStockObservable();

        NotificationObserver observer1 = new MobileAlertObserver("mb@gmail.com");
        NotificationObserver observer2 = new EmailAlertObserver("kt@gmail.com");
        NotificationObserver observer3 = new EmailAlertObserver("ram@gamil.com");

        iphoneStockObservable.add(observer1);
        iphoneStockObservable.add(observer2);
        iphoneStockObservable.add(observer3);

        iphoneStockObservable.setStock(10);
        iphoneStockObservable.setStock(0);
    }
}
