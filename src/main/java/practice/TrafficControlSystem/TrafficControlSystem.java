package practice.TrafficControlSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TrafficControlSystem {
    private static final TrafficControlSystem INSTANCE = new TrafficControlSystem();
    private List<IntersectionController> intersections = new ArrayList<>();
    private ExecutorService executorService;

    private TrafficControlSystem(){}

    public static TrafficControlSystem getInstance(){
        return INSTANCE;
    }

    public void addIntersection(int id, int greenDuration, int yellowDuration){
        IntersectionController intersection = new IntersectionController.Builder(id)
                .withDuration(greenDuration,yellowDuration)
                .build();

        intersections.add(intersection);
    }

    public void startSystem(){
        if(intersections.isEmpty()){
            System.out.println("No intersection to manage");
            return;
        }

        System.out.println("\n-- starting traffic controller System --\n");
        executorService = Executors.newFixedThreadPool(intersections.size());
        intersections.forEach(executorService::submit);
    }

    public void stopSystem(){
        System.out.println("-- Shutting Down traffic control system --");
        intersections.forEach(IntersectionController::stopService);
        executorService.shutdown();
        try{
           if(!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
               executorService.shutdown();
           }
        }catch(InterruptedException e){
            executorService.shutdown();
        }
        System.out.println("All intersections stopped. System shut down.");
    }


}
