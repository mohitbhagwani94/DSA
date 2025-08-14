package StrategyDesignPattern;

public class Main {
    public static void main(String[] args) {
        Vehicle offRoadVehicle = new OffRoadVehicle();
        offRoadVehicle.drive();

        Vehicle normalVehicle = new NormalVehicle();
        normalVehicle.drive();
    }
}
