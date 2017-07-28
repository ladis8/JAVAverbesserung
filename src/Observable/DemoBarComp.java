package Observable;

import java.awt.Dimension;
import javax.swing.JFrame;

public class DemoBarComp {

    void start() {
        JFrame frame = new JFrame("PJV - lec06 - Demo Progress Bar of the Computation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(480, 240));

        MyBarPanel myBarPanel = new MyBarPanel();

        frame.getContentPane().add(myBarPanel);
        frame.pack();
        frame.setVisible(true);

        //Model comp = new Model();
        ThreadModel comp = new ThreadModel();
        myBarPanel.setComputation(comp);
    }

    public static void main(String[] args) {
        DemoBarComp demo = new DemoBarComp();
        demo.start();
    }
}
