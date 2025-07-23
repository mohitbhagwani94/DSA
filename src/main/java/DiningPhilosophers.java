import java.util.concurrent.Semaphore;

class DiningPhilosophers {
    Semaphore semaphore;
    Semaphore forkSemaphore[];

    public DiningPhilosophers() {
        semaphore = new Semaphore(1);
        forkSemaphore = new Semaphore[5];

        for(int i=0;i<5;i++){
            forkSemaphore[i] = new Semaphore(1);
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        semaphore.acquire();
        int left = philosopher;
        int right = (philosopher+1)%5;

        Semaphore leftForkSemaphore = forkSemaphore[left];
        Semaphore rightForkSemaphore = forkSemaphore[right];
        if(philosopher==0){
            rightForkSemaphore.acquire();
            pickRightFork.run();
            leftForkSemaphore.acquire();
            pickLeftFork.run();
        } else {
            leftForkSemaphore.acquire();
            pickLeftFork.run();
            rightForkSemaphore.acquire();
            pickRightFork.run();
        }

        eat.run();

        putLeftFork.run();
        leftForkSemaphore.release();
        putRightFork.run();
        rightForkSemaphore.release();

        semaphore.release();
    }
}