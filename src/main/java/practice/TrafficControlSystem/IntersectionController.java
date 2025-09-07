package practice.TrafficControlSystem;

import practice.TrafficControlSystem.Enum.Directions;
import practice.TrafficControlSystem.States.Intersection.IntersectionState;
import practice.TrafficControlSystem.States.Intersection.NorthSouthGreenState;

import java.util.HashMap;
import java.util.Map;

public class IntersectionController implements Runnable {
    private int id;
    IntersectionState currentState;
    private Map<Directions, TrafficLight> lights;
    private int greenDuration;
    private int yellowDuration;
    private volatile boolean running = true;

    IntersectionController(int id, Map<Directions, TrafficLight> lights, int greenDuration, int yellowDuration) {
        this.id = id;
        this.lights = lights;
        this.greenDuration = greenDuration;
        this.yellowDuration = yellowDuration;
        this.currentState = new NorthSouthGreenState();
    }
    public int getId(){
        return id;
    }

    public TrafficLight getLight(Directions direction){
        return lights.get(direction);
    }

    public int getGreenDuration(){
        return greenDuration;
    }

    public int getYellowDuration(){
        return yellowDuration;
    }

    public TrafficLight getLights(Directions dir){
        return lights.get(dir);
    }

    public void setState(IntersectionState state){
        currentState = state;
    }

    public void stopService(){
        running = false;
    }

    public void run(){
        while(running){
            try {
                currentState.handle(this);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Intersection id :" + id + " was interrupted.");
                running = false;
            }
        }
    }

    public static class Builder{
        private int id;
        private int yellowDuration = 5000;
        private int greenDuration = 2000;

        public Builder(int id){
            this.id = id;
        }

        public Builder withDuration(int green, int yellow){
            this.yellowDuration =yellow;
            this.greenDuration = green;
            return this;
        }

        // add observer method

        public IntersectionController build(){
            Map<Directions, TrafficLight> lights = new HashMap<>();
            for(Directions dir : Directions.values()){
                TrafficLight light = new TrafficLight(id,dir);
                //++ Observer
                lights.put(dir,light);
            }
            return new IntersectionController(id,lights, greenDuration, yellowDuration);
        }
    }
}
