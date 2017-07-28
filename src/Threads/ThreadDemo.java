package Threads;

import java.util.ArrayList;

public class ThreadDemo {

    public static void main(String[] args) throws Exception {

        createThreads(4);
    }

    private static void createThreads(int numberOfThreads) throws InterruptedException {

        ArrayList<Worker> threads = new ArrayList();

        //create threads
        for (int i = 0; i < numberOfThreads; ++i)
            threads.add(new Worker(i));

        // start threads
        for (Thread thread : threads) {
            thread.setDaemon(true); //propery - must be first
            thread.start();
        }

        //create killer thread
        Thread killer = new Thread(new ThreadKiller(threads, 3));
        killer.setDaemon(true);
        killer.start();
        killer.join();
    }
}
