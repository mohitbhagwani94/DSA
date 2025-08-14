package Mutlithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class SharedResources {
    Queue<Item> queue = new LinkedList<Item>();

    public  void addItem(int no) throws InterruptedException {
        System.out.println("addItem method is invoked by :"+ Thread.currentThread().getName());
        System.out.println("Enter Queue" +queue+ " queue size: "+queue.size());
        while(queue.size()>=10){
            System.out.println("addItem Thread in the wait state");
            wait();
        }
        if(queue.size()<10) {
            queue.add(new Item(no));
            System.out.println("Item added by thread: " + Thread.currentThread().getName()+" and invoke all threads which are in wait state and query size:"+queue.size());
           // System.out.println("Item added by thread: " + Thread.currentThread().getName()+" and invoke all threads which are in wait state");
            notifyAll();
        }
        System.out.println("Exist Queue" +queue+ " queue size"+queue.size());
    }

    public    void consumeItem() throws InterruptedException {
        System.out.println("Consume Item Method invoked by:"+ Thread.currentThread().getName());
        while(queue.isEmpty()){
                System.out.println(" consumeItem Thread in the wait state");
                wait();
               // Thread.sleep(3000);

        }
        if(!queue.isEmpty()) {
            queue.remove();
            System.out.println("By removal Queue Size:" + queue.size());
            System.out.println("Item removed by thread:" + Thread.currentThread().getName());
        }
        notifyAll();
    }
}

class CustomRejectHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

    }
}