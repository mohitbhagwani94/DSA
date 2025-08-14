package AbstractDesignPattern;

import AbstractDesignPattern.Strategy.NormalDriveStrategy;

public class GoodsVehicle extends Vehicle{
    GoodsVehicle(){
        super(new NormalDriveStrategy());
    }
}
