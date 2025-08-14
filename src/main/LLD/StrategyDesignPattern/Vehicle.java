package StrategyDesignPattern;

import StrategyDesignPattern.Strategy.DriveStrategy;

public class Vehicle {
    DriveStrategy driveStrategy;
    Vehicle(DriveStrategy driveObj){
        this.driveStrategy = driveObj;
    }
    public void drive(){
        driveStrategy.drive();
    }
}
