package practice.TrafficControlSystem.States.Light;

import practice.TrafficControlSystem.Enum.LightColor;
import practice.TrafficControlSystem.TrafficLight;

public class YellowState implements SignalState{
    @Override
    public void handle(TrafficLight context) {
        context.setColor(LightColor.YELLOW);
        context.setNextState(new RedState());
    }
}
