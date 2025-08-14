package Mutlithreading;

public class SharedResources2 extends Thread {

    @Override
    public void run(){
        System.out.println(" Code running from thread - "+ Thread.currentThread().getName());
    }

}
