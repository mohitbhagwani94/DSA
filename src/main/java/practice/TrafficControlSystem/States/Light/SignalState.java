package practice.TrafficControlSystem.States.Light;

import practice.TrafficControlSystem.TrafficLight;

public interface SignalState {
    void handle(TrafficLight Context);
}
