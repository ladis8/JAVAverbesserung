package Observable;

import java.util.Observable;
import java.util.Observer;

public class ThreadModel extends Model implements Runnable {

    Thread thread;
    volatile boolean computing = false;
    volatile boolean stop = false;
    final Object end;

    private class UpdateNotificator extends Observable {

        private void update() {
            setChanged();
            notifyObservers();
        }
    }

    UpdateNotificator updateNotificator;

    public ThreadModel() {
        updateNotificator = new UpdateNotificator();
        end = new Object();
    }

    @Override
    public void registerObserver(Observer observer) {
        updateNotificator.addObserver(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {updateNotificator.deleteObserver(observer);}

    @Override
    public synchronized void compute() {
        if (computing == false) {
            computing = true;
            thread = new Thread(this);
            thread.start();
            System.out.println("ThreadModel start computation thread " + thread.getId());

        }
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);
        updateNotificator.update();
    }

    public synchronized void stopComputation() {
        if (thread != null) {
            System.out.println("Stopping the thread " + thread.getId());
            synchronized (end) {
                stop = true;
            }

            try {
                System.out.println("stopComputation: call join");
                thread.join();
                System.out.println("stopComputation: join end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + thread.getId() + " stopped");
            thread = null;
        }
    }

    @Override
    public void run() {
        //restartComputation();
        boolean finished = false;
        while (!computePart() && !finished) {
            updateNotificator.update();
            System.out.println("run check end");
            synchronized (end) {
                finished = stop;
                System.out.println("Run: " + finished);
            }
        }
        System.out.println("Run: Synchronized this");
        synchronized (end) {
            computing = false;
        }
    }
}
