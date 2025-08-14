package StrategyDesignPattern;

import AbstractDesignPattern.Strategy.SportDriveStrategy;
import StrategyDesignPattern.Strategy.SportsDrive;

public class OffRoadVehicle extends Vehicle{
    OffRoadVehicle(){
        super( new SportsDrive());
    }
}
