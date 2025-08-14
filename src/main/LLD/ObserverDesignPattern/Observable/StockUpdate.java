package Practice.ObserverDesignPattern.Observable;

import Practice.ObserverDesignPattern.Observer.NotificationObserver;

public interface StockUpdate {
    public void add(NotificationObserver observer);
    public void remove(NotificationObserver observer);
    public void notifyUpdate();
    public void setStock(int stock);
}
