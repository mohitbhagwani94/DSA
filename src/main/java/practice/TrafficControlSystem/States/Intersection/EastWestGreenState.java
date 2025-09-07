package practice.TrafficControlSystem.States.Intersection;

import practice.TrafficControlSystem.Enum.Directions;
import practice.TrafficControlSystem.Enum.LightColor;
import practice.TrafficControlSystem.IntersectionController;

public class EastWestGreenState implements IntersectionState{
    @Override
    public void handle(IntersectionController context) throws InterruptedException {
        System.out.printf("\n --- Intersection id: %d Cycle Start -> East West Green --- \n", context.getId());

        context.getLight(Directions.NORTH).setColor(LightColor.RED);
        context.getLight(Directions.SOUTH).setColor(LightColor.RED);

        context.getLight(Directions.EAST).startGreen();
        context.getLight(Directions.WEST).startGreen();

        Thread.sleep(context.getGreenDuration());

        context.getLight(Directions.EAST).transition();
        context.getLight(Directions.WEST).transition();

        Thread.sleep(context.getYellowDuration());

        context.getLight(Directions.EAST).transition();
        context.getLight(Directions.WEST).transition();

        context.setState(new NorthSouthGreenState());
    }
}
