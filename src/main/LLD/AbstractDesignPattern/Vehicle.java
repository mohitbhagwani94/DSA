package AbstractDesignPattern;

import AbstractDesignPattern.Strategy.DriveStrategy;

public class Vehicle {
    DriveStrategy driveObj;

    //This is known as constructor injection
    Vehicle(DriveStrategy driveObj){
        this.driveObj = driveObj;
    }

    public void drive(){
        driveObj.drive();
    }

}
