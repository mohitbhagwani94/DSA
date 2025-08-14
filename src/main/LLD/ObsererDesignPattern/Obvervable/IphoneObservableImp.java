package ObsererDesignPattern.Obvervable;

import ObsererDesignPattern.Obverver.NotificationAlertObserver;

import java.util.ArrayList;
import java.util.List;

public class IphoneObservableImp implements StockObservable {
    public List<NotificationAlertObserver> observerList = new ArrayList<>();
    public int stockCount=0;

    @Override
    public void add(NotificationAlertObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscriber() {
        for(NotificationAlertObserver observer : observerList){
            observer.update();
        }
    }

    @Override
    public void setData(int noOfnewStock) {
        if(stockCount ==0){
            notifySubscriber();
        }
        stockCount +=noOfnewStock;
    }

    @Override
    public int getData() {
        return stockCount;
    }
}
