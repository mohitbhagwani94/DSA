package AbstractFactoryDesignPattern;

public class AsusManufacturer extends Company{

    @Override
    public GPU createGPU() {
        return new AsusGpu();
    }

    @Override
    public Monitor createMonitor() {
        return new AsusMonitor();
    }
}
