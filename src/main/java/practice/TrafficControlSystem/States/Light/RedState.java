package practice.TrafficControlSystem.States.Light;

import practice.TrafficControlSystem.Enum.LightColor;
import practice.TrafficControlSystem.TrafficLight;

public class RedState implements SignalState{
    @Override
    public void handle(TrafficLight context) {
        context.setColor(LightColor.RED);
        context.setNextState(new GreenState());
    }
}
