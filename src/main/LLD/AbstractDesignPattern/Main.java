package AbstractDesignPattern;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new OffRoadVehicle();
        vehicle.drive();

        Vehicle sportsVehicle = new SportsVehicle();
        sportsVehicle.drive();
    }
}
