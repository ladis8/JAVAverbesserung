package Threads;


public class Worker extends Thread {

    private volatile boolean stop;

    public Worker(int id) {
        super("Worker " + id);
        stop = false;
        System.out.println("Worker id: " + id + " has been created threadID:" + getId());
    }

    public void run() {
       while (true){
            if (stop) {
                System.out.println("Thread '" + getName() + "' receives a request to stop");
                break;
            }
           try {
               Thread.sleep(1000); //sleep for 0.5 second
               System.out.println("This thread '" + getName() + "' threadID: " + getId() + " is working");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }



        }
    }

    public void shutdown() {stop = true;}


}