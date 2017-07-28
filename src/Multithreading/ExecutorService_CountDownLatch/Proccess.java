package Multithreading.ExecutorService_CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ladislav on 18.6.17.
 */
public class Proccess implements Runnable {

    private CountDownLatch latch;
    private int id;
    private Random generator;

    public Proccess(int id){
        this.id = id;
        generator = new Random();
    }
    public Proccess (int id, CountDownLatch latch){
        this(id);
        this.latch = latch;
    }


    @Override
    public void run() {
        System.out.println("Thread "+id + " activated");
        try {
            //delay that represents some calculation
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thred " + id +" finished the task");

        if (latch!=null){
            latch.countDown();
        }
    }

}
