package practice.TrafficControlSystem;


import practice.TrafficControlSystem.Enum.Directions;
import practice.TrafficControlSystem.Enum.LightColor;
import practice.TrafficControlSystem.States.Light.GreenState;
import practice.TrafficControlSystem.States.Light.RedState;
import practice.TrafficControlSystem.States.Light.SignalState;

public class TrafficLight {
    int intersectionId;
    private SignalState currentState;
    private SignalState nextState;
    private Directions directions;
    private LightColor currentLightColor;

    TrafficLight(int intersectionId, Directions dir) {
        this.intersectionId = intersectionId;
        this.currentState = new RedState();
        this.directions = dir;
        this.currentState.handle(this);
        //handle
    }

    public void startGreen() {
        this.currentState = new GreenState();
        this.currentState.handle(this);
    }

    public void transition(){
        this.currentState = this.nextState;
        this.currentState.handle(this);
    }


    public void setNextState(SignalState signalState) {
        this.nextState = signalState;
    }

    public void setColor(LightColor lightColor) {
        if(this.currentLightColor != lightColor ){
            this.currentLightColor = lightColor;
        }
    }
}
