package Observable;

import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class Model {

    int done;
    int part;

    public Model() {
        done = 0;
        part = 1;
    }

    public void restartComputation() {
        done = 0;
        part = 1;
    }

    public int getProgress() {
        return done;
    }

    public void setValue(int value) {
        done = value < 0 ? 0 : value > 100 ? 100 : value;
    }

    public void compute() {
        restartComputation();
        for (int i = 1; i <= 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Computation has been interupted");
                }
                done++;

            }
            System.out.println(10 * i + "% of the computation done");
        }
    }

    public boolean computePart() {
        if (part <= 10) {
            for (int j = 0; j < 10; ++j) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Computation has been interupted");
                }
                done++;
            }
            System.out.println(10 * part + "% of the computation done");
            part++;
        }
        return (part > 11);
    }


    public void unregisterObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public void registerObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public void stopComputation() {
        //nothing to do here
    }


}
