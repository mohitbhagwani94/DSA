package AbstractFactoryDesignPattern;

public class Client {
    public static void main(String arg[]){
        Company msi = new MsiManufacturer();
        GPU msiGpu = msi.createGPU();
        Monitor msiMonitor = msi.createMonitor();

        Company asus = new AsusManufacturer();
        GPU asusGpu = asus.createGPU();
        Monitor asusMonitor = asus.createMonitor();

    }
}
