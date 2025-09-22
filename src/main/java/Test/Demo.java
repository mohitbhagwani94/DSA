package Test;

class Demo {
    private static Demo INSTANCE = new Demo();
    private Demo(){}

    public static synchronized Demo getInstance(){
        return INSTANCE;
    }
}

class DemoTest{
    public static void main(String[] args) {
        System.out.println(Demo.getInstance());
        System.out.println(Demo.getInstance());
        System.out.println(Demo.getInstance());
    }
}
