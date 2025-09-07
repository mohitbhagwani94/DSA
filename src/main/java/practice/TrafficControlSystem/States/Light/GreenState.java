package practice.TrafficControlSystem.States.Light;

import practice.TrafficControlSystem.TrafficLight;
import practice.TrafficControlSystem.Enum.LightColor;

public class GreenState implements SignalState{
    @Override
    public void handle(TrafficLight context) {
        context.setColor(LightColor.GREEN);
        context.setNextState(new YellowState());
    }
}
