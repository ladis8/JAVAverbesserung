package Observable;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Demonstration of various action listeners and ways how create gui. It is not
 * intended to be fully working example and usage of the best techniques. Be
 * aware of this comment and use the code for inspiration.
 */
public class MyBarPanel extends JPanel implements Observer {

    JTextField status;
        JProgressBar bar;

        @Override
        public void update(Observable o, Object arg) {
            updateProgress();
        }

    private class SimpleButtonListener implements ActionListener {

        final String msg;

        public SimpleButtonListener(String msg) {
            this.msg = msg;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            status.setText(msg);
        }
    }

    private class ToggleButtonListener implements ActionListener {

        final String msg;
        boolean state;

        public ToggleButtonListener(String msg) {
            this.msg = msg;
            state = false;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            state = !state;
            status.setText(msg + " " + (state ? "On" : "Off"));
        }
    }

    private class ButtonListener implements ActionListener {

        int count = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            count += 1;
            System.out.println("ButtonListener: event: " + e);
            System.out.println("ButtonListener: e.getSource: " + e.getSource());
            System.out.println("ActionCommand: " + e.getActionCommand());
            JButton btn = (JButton) e.getSource();
            status.setText("ButtonListener: received new event " + count + " from " + btn.getText());
        }
    }

    private class ComputeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            status.setText("Start computation");
            computation.compute();
        }
    }

    private class StopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            computation.stopComputation();
            status.setText("Computation stopped");

        }
    }

    Model computation;

    public MyBarPanel() {
        computation = null;
        createComponents();
    }

    public void setComputation(Model computation) {
        if (this.computation != null) {
            this.computation.unregisterObserver(this);
        }
        this.computation = computation;
        this.computation.registerObserver(this);
    }

    private void createComponents() {
        JPanel controlButtonsPanel = new JPanel();
        createControlButtons(controlButtonsPanel);

        JPanel buttonsPanel = new JPanel();
        createButtons(buttonsPanel);

        bar = new JProgressBar(0, 100); // 0-100%
        JPanel progressPanel = new JPanel();
        createProgress(progressPanel, bar);

        status = createStatusBar("Waiting for your commands");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(controlButtonsPanel);
        add(buttonsPanel);
        add(progressPanel);
        add(status);
    }

    private JPanel createButtons(JPanel panel) {
        JButton btn1 = new JButton("#1");
        JButton btn2 = new JButton("#2");
        final JButton btn3 = new JButton("#3");
        JButton btn4 = new JButton("#4");

        btn1.addActionListener(new SimpleButtonListener("Button #1 pressed"));
        btn2.addActionListener(new SimpleButtonListener("Button #2 pressed"));
        btn2.addActionListener(new ToggleButtonListener("Button #2"));
        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btn3.setText("clicked");
            }
        });
        ButtonListener buttonListener = new ButtonListener();

        btn4.addActionListener(buttonListener);
        btn1.addActionListener(buttonListener);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (computation != null) {
                    computation.setValue(10);
                }
                //updateProgress();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (computation != null) {
                    computation.setValue(40);
                }
                //updateProgress();
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (computation != null) {
                    computation.setValue(70);
                }
                //updateProgress();
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (computation != null) {
                    computation.setValue(80);
                }
                //updateProgress();
            }
        });

        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        return panel;
    }

    private void updateProgress() {
        if (computation != null) {
            bar.setValue(computation.getProgress());
        }
    }

    private JPanel createControlButtons(JPanel panel) {
        JButton btnCompute = new JButton("Compute");
        JButton btnStop = new JButton("Stop");
        JButton btnQuit = new JButton("Quit");

        btnCompute.addActionListener(new ComputeListener());
        btnStop.addActionListener(new StopListener());
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Force quit");
                System.exit(0);
            }
        });

        panel.add(btnCompute);
        panel.add(btnStop);
        panel.add(btnQuit);
        return panel;

    }

    private JPanel createProgress(final JPanel panel, JProgressBar progress) {
        final TitledBorder border = BorderFactory.createTitledBorder("Computations");
        panel.setBorder(border);
        panel.add(progress);
        progress.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("mouseEntered");
                border.setTitle("Mouse entered to the bar area");
                panel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("mouseExit");
                border.setTitle("Computations");
                panel.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });

        progress.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("Mouse in progress bar at (" + e.getX() + ", " + e.getY() + ")");
            }
        });
        return panel;
    }

    private JTextField createStatusBar(String initMessage) {
        JTextField statusBar = new JTextField();
        statusBar.setEditable(false);
        statusBar.setText(initMessage);
        statusBar.setHorizontalAlignment(JTextField.LEFT);
        statusBar.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        statusBar.getPreferredSize().height
                ));
        return statusBar;
    }
}
