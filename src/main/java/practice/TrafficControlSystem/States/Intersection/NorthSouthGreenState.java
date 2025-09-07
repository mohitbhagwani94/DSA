package practice.TrafficControlSystem.States.Intersection;

import practice.TrafficControlSystem.Enum.Directions;
import practice.TrafficControlSystem.Enum.LightColor;
import practice.TrafficControlSystem.IntersectionController;

public class NorthSouthGreenState implements IntersectionState{
    @Override
    public void handle(IntersectionController context) throws InterruptedException {
        System.out.printf("\n --- Intersection id: %d Cycle Start -> North South Green --- \n", context.getId());

        context.getLight(Directions.NORTH).startGreen();
        context.getLight(Directions.SOUTH).startGreen();

        context.getLight(Directions.EAST).setColor(LightColor.RED);
        context.getLight(Directions.WEST).setColor(LightColor.RED);

        Thread.sleep(context.getGreenDuration());

        context.getLight(Directions.NORTH).transition();
        context.getLight(Directions.SOUTH).transition();

        Thread.sleep(context.getYellowDuration());

        context.getLight(Directions.NORTH).transition();
        context.getLight(Directions.SOUTH).transition();

        context.setState(new EastWestGreenState());
    }
}
