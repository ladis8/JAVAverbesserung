package Multithreading.ExecutorService_CountDownLatch;

import java.util.concurrent.*;

/**
 * Created by ladislav on 18.6.17.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        //creates thread pool with fixed num of threads
        //thread pool starts tasks automatically
        ExecutorService executor = Executors.newFixedThreadPool(3);
        int NUMOFTASKS = 6;


        //firt way how to submit tasks with Executorservice only
        for (int i = 0; i <NUMOFTASKS ; i++) {
            executor.submit(new Proccess(i));
        }
        System.out.println("All tasks were submitted");
        executor.shutdown();
        //wait 30 second until termination
        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("All tasks has been completed");
        System.out.println("\n");


        //Version with Countdownlatch
        CountDownLatch latch = new CountDownLatch(NUMOFTASKS);
        executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i <NUMOFTASKS ; i++) {
            executor.submit(new Proccess(i, latch));
        }
        //waits until the latch is 0
        System.out.println("All tasks were submitted");
        executor.shutdown();
        latch.await();
        System.out.println("All tasks has been completed");


    }
}
