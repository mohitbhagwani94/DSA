package Test.Elevator;

public class ElevatorCar {
    int id;
    int currentFloor;

    public ElevatorCar(int id, int currentFloor, Direction dir, ElevatorState state) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.dir = dir;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDir() {
        return dir;
    }

    public ElevatorState getState() {
        return state;
    }

    Direction dir;
    ElevatorState state;



}
/*SCAN algo

* */

