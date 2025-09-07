package practice.TrafficControlSystem;

import java.util.concurrent.TimeUnit;

public class TrafficSystemDemo {

    public static void main(String arg[]) {
        TrafficControlSystem system = TrafficControlSystem.getInstance();

        system.addIntersection(1, 500, 200);
        system.addIntersection(2,700,150);

        system.startSystem();

        try{
            TimeUnit.SECONDS.sleep(5);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        system.stopSystem();
    }

}
