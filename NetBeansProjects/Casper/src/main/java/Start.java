
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RonJya
 */
public class Start extends OurFrame {

    private Start S = this;
    private ImageSet backgroundImg;
    private JLabel StartLabel, Tutorialabel, QuitLabel;
    private JButton CreditButton;

    public Start() {
        super("Start");
        setIconImage(new ImageSet("resource/logo.png").getImage());
        contentpane.setLayout(new BorderLayout());
        addcomponent();

    }

    public void addcomponent() {
        backgroundImg = new ImageSet("resource/MenuBG.png").resize(1366, 768);

        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);

        StartLabel = new JLabel(new ImageSet("resource/Start.png").resize(350, 50));
        StartLabel.setBounds(250, 300, 350, 50);

        Tutorialabel = new JLabel(new ImageSet("resource/Tutorial.png").resize(350, 50));
        Tutorialabel.setBounds(250, 390, 350, 50);

        CreditButton = new JButton("Credit");
        CreditButton.setBounds(797, 450, 120, 50);

        QuitLabel = new JLabel(new ImageSet("resource/Exit.png").resize(350, 50));
        QuitLabel.setBounds(250, 480, 350, 50);

        addListener();
        drawpane.add(StartLabel);
        drawpane.add(Tutorialabel);
        drawpane.add(CreditButton);
        drawpane.add(QuitLabel);
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();

    }

    public void addListener() {
        StartLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                //new Start(s).setVisible(true); Start game

            }
        });

        Tutorialabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                new Tutorial(S).setVisible(true);

            }
        });
        CreditButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                new Credit(S).setVisible(true);

            }
        });
        QuitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });

    }

    public static void main(String args[]) {
        new Start();

    }
}

class Tutorial extends OurFrame {

    private Start S;
    private ImageSet backgroundImg;
    private JLabel BackLabel, LeftLabel, RightLabel;
    private JLabel[] tutorLabel = new JLabel[3];
    private int i = 0;

    public Tutorial(Start s) {
        super("Tutorial");
        S = s;

        contentpane.setLayout(new BorderLayout());
        contentpane.setBackground(Color.LIGHT_GRAY);

        addcomponent();
    }

    public void addcomponent() {
        backgroundImg = new ImageSet("resource/TutorialBG.png").resize(1366, 768);

        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);

        BackLabel = new JLabel(new ImageSet("resource/back.png").resize(64, 64));
        BackLabel.setBounds(0, 0, 64, 64);

        LeftLabel = new JLabel(new ImageSet("resource/back.png").resize(64, 64));
        LeftLabel.setBounds(0, 300, 64, 64);

        RightLabel = new JLabel(new ImageSet("resource/back.png").resize(64, 64));
        RightLabel.setBounds(1280, 300, 64, 64);

        tutorLabel[0] = new JLabel(new ImageSet("resource/tutor1.png").resize(1087, 613));
        tutorLabel[0].setBounds(75, 50, 1087, 613);

        tutorLabel[1] = new JLabel(new ImageSet("resource/tutor2.png").resize(1087, 613));
        tutorLabel[1].setBounds(75, 50, 1087, 613);
        tutorLabel[1].setVisible(false);

        tutorLabel[2] = new JLabel(new ImageSet("resource/tutor3.png").resize(1087, 613));
        tutorLabel[2].setBounds(75, 50, 1087, 613);
        tutorLabel[2].setVisible(false);

        addListener();

        drawpane.add(BackLabel);
        drawpane.add(LeftLabel);
        drawpane.add(RightLabel);
        drawpane.add(tutorLabel[0]);
        drawpane.add(tutorLabel[1]);
        drawpane.add(tutorLabel[2]);

        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }

    public void addListener() {
        BackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                S.setVisible(true);
                dispose();
            }
        });
        LeftLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (i > 0) {
                    tutorLabel[i].setVisible(false);
                    i--;
                    tutorLabel[i].setVisible(true);
                }

            }
        });
        RightLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (i < 2) {
                    tutorLabel[i].setVisible(false);
                    System.out.println(i);
                    i++;
                    System.out.println(i);
                    tutorLabel[i].setVisible(true);
                }

            }
        });
    }

}

class Credit extends OurFrame {

    private Start S;
    private ImageSet backgroundImg;
    private JLabel BackLabel, CreditLabel;

    public Credit(Start s) {
        super("Credit");
        S = s;

        contentpane.setLayout(new BorderLayout());
        contentpane.setBackground(Color.LIGHT_GRAY);

        addcomponent();
    }

    public void addcomponent() {
        backgroundImg = new ImageSet("resource/creditBG.png").resize(1366, 768);

        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);

        CreditLabel = new JLabel(new ImageSet("resource/Creditpeople.png").resize(1087, 613));
        CreditLabel.setBounds(150, 50, 1087, 613);

        BackLabel = new JLabel(new ImageSet("resource/back.png").resize(64, 64));
        BackLabel.setBounds(0, 0, 64, 64);

        addListener();

        drawpane.add(BackLabel);
        drawpane.add(CreditLabel);
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }

    public void addListener() {
        BackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                S.setVisible(true);
                dispose();
            }
        });
    }

}
