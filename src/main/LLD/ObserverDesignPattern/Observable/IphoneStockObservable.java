package Practice.ObserverDesignPattern.Observable;

import Practice.ObserverDesignPattern.Observer.NotificationObserver;

import java.util.ArrayList;
import java.util.List;

public class IphoneStockObservable implements StockUpdate{

    List<NotificationObserver> lists = new ArrayList<NotificationObserver>();
    int stockValue = 0;
    @Override
    public void add(NotificationObserver observer) {
        lists.add(observer);
    }

    @Override
    public void remove(NotificationObserver observer) {
        lists.remove(observer);
    }

    @Override
    public void notifyUpdate() {
        for(NotificationObserver list : lists){
            list.update();
        }
    }

    @Override
    public void setStock( int value) {
        if(stockValue == 0){
            notifyUpdate();
        }
        stockValue = value;
    }
}
