package practice.TrafficControlSystem.Observer;

import practice.TrafficControlSystem.Enum.Directions;
import practice.TrafficControlSystem.Enum.LightColor;

public class CentralMonitor implements TrafficObserver{
    @Override
    public void update(int intersectionId, Directions dir, LightColor color) {
        System.out.printf("[Monitor] Intersection id %d: Light for %s direction changed to %s \n", intersectionId,dir,color);
    }
}
