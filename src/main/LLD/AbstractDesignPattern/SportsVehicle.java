package AbstractDesignPattern;

import AbstractDesignPattern.Strategy.SportDriveStrategy;

public class SportsVehicle extends Vehicle{
    SportsVehicle(){
        super( new SportDriveStrategy());
    }
}
