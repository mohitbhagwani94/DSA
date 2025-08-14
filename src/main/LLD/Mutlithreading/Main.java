package Mutlithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        SharedResources srthread =  new SharedResources();

        Thread producerThread = new Thread(()->{
            System.out.println("producer Thread "+ Thread.currentThread().getName());
            for(int i=0;i<20;i++) {
                try {
                    srthread.addItem(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });

        Thread consumerThread = new Thread(()->{
            System.out.println("Consumer Thread "+ Thread.currentThread().getName());
            try {
                for(int i=0;i<20;i++){
                    srthread.consumeItem();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producerThread.start();
        consumerThread.start();
        System.out.println(" End of main block --"+ Thread.currentThread().getName());
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->"hello");
    }
}
