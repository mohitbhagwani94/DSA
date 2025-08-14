package Practice.ObserverDesignPattern.Observer;

public class MobileAlertObserver implements NotificationObserver{

    String emailID;

    public MobileAlertObserver(String emailID){
        this.emailID = emailID;
    }

    @Override
    public void update() {
        System.out.println(" Mobile Alert send to" + emailID);
    }
}
