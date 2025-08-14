package StrategyDesignPattern;

import StrategyDesignPattern.Strategy.NormalDrive;

public class NormalVehicle extends Vehicle {
    NormalVehicle(){
        super(new NormalDrive());
    }

}
