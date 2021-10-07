
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class PIC extends JFrame  {

    private JLabel label;

    public PIC() {
        setTitle("MovingPIC");
        label = new JLabel(new ImageIcon("resource/1.png"));
        add(label, BorderLayout.CENTER);

        setSize(1280, 720);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        move();
    }

    void move() {
        ////////////////////////////////
        // part of edit responding David kroukamp  
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                
            }
        });
        t.start();//start thread

    }

    public static void main(String[] args) {
        new PIC();
    }
}
