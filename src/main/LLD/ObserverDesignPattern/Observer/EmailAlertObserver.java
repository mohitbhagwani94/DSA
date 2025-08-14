package Practice.ObserverDesignPattern.Observer;

public class EmailAlertObserver implements NotificationObserver{

    String email;

    public EmailAlertObserver(String email){
        this.email = email;
    }

    @Override
    public void update() {
        System.out.println("Email send to "+ email);
    }
}
