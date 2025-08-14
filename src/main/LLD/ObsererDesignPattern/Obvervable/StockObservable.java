package ObsererDesignPattern.Obvervable;

import ObsererDesignPattern.Obverver.NotificationAlertObserver;

public interface StockObservable {
    public void add(NotificationAlertObserver observer);
    public void remove(NotificationAlertObserver observer);
    public void notifySubscriber();
    public void setData(int noOfnewStock);
    public int getData();
}
