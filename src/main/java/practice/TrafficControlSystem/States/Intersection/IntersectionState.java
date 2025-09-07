package practice.TrafficControlSystem.States.Intersection;

import practice.TrafficControlSystem.IntersectionController;

public interface IntersectionState {
    void handle(IntersectionController context) throws InterruptedException;
}
