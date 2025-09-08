package practice.TrafficControlSystem.Observer;

import practice.TrafficControlSystem.Enum.Directions;
import practice.TrafficControlSystem.Enum.LightColor;

public interface TrafficObserver {
    void update(int intersectionId, Directions dir, LightColor color);
}
