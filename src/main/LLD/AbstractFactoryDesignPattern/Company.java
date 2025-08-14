package AbstractFactoryDesignPattern;

public abstract class Company {
    //business logic can be added here
    public abstract GPU createGPU();
    public abstract Monitor createMonitor();
}
