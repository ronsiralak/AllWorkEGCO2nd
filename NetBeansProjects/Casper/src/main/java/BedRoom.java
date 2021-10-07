
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
public class BedRoom extends OurFrame {

    private BedRoom BR = this;
    private ImageSet backgroundImg, BedImg;
    private JLabel BedLabel, TeddyLabel, ComLabel, GotoCenterLabel;

    public BedRoom() {
        super("Bed room");
        setIconImage(new ImageSet("resource/logo.png").getImage());
        contentpane.setLayout(new BorderLayout());
        addcomponent();

    }

    public void addcomponent() {
        backgroundImg = new ImageSet("resource/bedroomBG.jpg");
        BedImg = new ImageSet("resource/bed.png").resize(200, 400);

        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);

        BedLabel = new JLabel(BedImg);
        BedLabel.setBounds(1100, 200, 200, 400);

        TeddyLabel = new JLabel(new ImageSet("resource/teddy.png").resize(150, 100));
        TeddyLabel.setBounds(1200, 50, 150, 100);

        GotoCenterLabel = new JLabel(new ImageSet("resource/leftarrow.png").resize(150, 100));
        GotoCenterLabel.setBounds(0, 300, 150, 100);

        ComLabel = new JLabel(new ImageSet("resource/com.png").resize(200, 200));
        ComLabel.setBounds(600, 500, 200, 200);

        addListener();
        drawpane.add(BedLabel);
        drawpane.add(TeddyLabel);
        drawpane.add(GotoCenterLabel);
        drawpane.add(ComLabel);
        contentpane.add(drawpane, BorderLayout.CENTER);

        validate();

    }

    public void addListener() {
        TeddyLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                new Teddypuzzle(BR).setVisible(true);

            }
        });
        ComLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                new Compuzzle(BR).setVisible(true);

            }
        });
        GotoCenterLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                //new TV().setVisible(true); 
            }
        });

    }

    /*public static void main(String args[]) {
        new BedRoom();
    }*/
}

class Compuzzle extends OurFrame {

    private static String PassWord = "11244";
    private BedRoom BR;
    private JLabel PassLabel, BackLabel, CongratLabel, Duck1, Duck2, Duck3;
    private ImageSet duckPurple, duckCyan, duckBlue, duckGreen, duckYellow, duckOrange, duckRed;
    private JPasswordField JPass;
    private JButton summit, summitduck;
    private JComboBox combobox1, combobox2, combobox3;

    public Compuzzle(BedRoom B) {
        super("Computer");
        BR = B;
        contentpane.setLayout(new BorderLayout());
        contentpane.setBackground(Color.LIGHT_GRAY);

        addcomponent();
    }

    public void addcomponent() {
        duckPurple = new ImageSet("resource/duckPurple.png").resize(100, 100);
        duckCyan = new ImageSet("resource/duckCyan.png").resize(100, 100);
        duckBlue = new ImageSet("resource/duckBlue.png").resize(100, 100);
        duckGreen = new ImageSet("resource/duckGreen.png").resize(100, 100);
        duckYellow = new ImageSet("resource/duckYellow.png").resize(100, 100);
        duckOrange = new ImageSet("resource/duckOrange.png").resize(100, 100);
        duckRed = new ImageSet("resource/duckRed.png").resize(100, 100);
        PassLabel = new JLabel("Need Password to Get Data");
        PassLabel.setBounds(500, 150, 500, 100);
        PassLabel.setFont(new Font("Arial", Font.PLAIN, 28));

        JPass = new JPasswordField(10);
        JPass.setBounds(600, 350, 200, 50);
        JPass.setFont(new Font("Arial", Font.BOLD, 40));
        JPass.setEchoChar('*');

        Duck1 = new JLabel(duckRed);
        Duck1.setBounds(250, 200, 100, 100);
        Duck2 = new JLabel(duckBlue);
        Duck2.setBounds(650, 200, 100, 100);
        Duck3 = new JLabel(duckYellow);
        Duck3.setBounds(1050, 200, 100, 100);

        String color[] = {"Red", "Orange", "Yellow", "Green", "Blue", "Cyan", "Purple"};
        combobox1 = new JComboBox(color);
        combobox1.setBounds(250, 350, 100, 20);
        combobox2 = new JComboBox(color);
        combobox2.setBounds(650, 350, 100, 20);
        combobox3 = new JComboBox(color);
        combobox3.setBounds(1050, 350, 100, 20);

        BackLabel = new JLabel(new ImageSet("resource/back.png").resize(64, 64));
        BackLabel.setBounds(0, 0, 64, 64);

        summit = new JButton("Login");
        summit.setBounds(600, 500, 200, 50);

        summitduck = new JButton("Check!!");
        summitduck.setBounds(600, 500, 200, 50);

        CongratLabel = new JLabel("Congrat door password is 07347");
        CongratLabel.setBounds(350, 150, 700, 300);
        CongratLabel.setFont(new Font("Arial", Font.PLAIN, 40));

        addListener();
        drawpane = new JLabel();
        drawpane.setLayout(null);
        drawpane.add(JPass);
        drawpane.add(Duck1);
        drawpane.add(Duck2);
        drawpane.add(Duck3);
        drawpane.add(combobox1);
        drawpane.add(combobox2);
        drawpane.add(combobox3);
        drawpane.add(PassLabel);
        drawpane.add(summit);
        drawpane.add(summitduck);
        drawpane.add(CongratLabel);
        drawpane.add(BackLabel);
        contentpane.add(drawpane, BorderLayout.CENTER);

        CongratLabel.setVisible(false);
        Duck1.setVisible(false);
        Duck2.setVisible(false);
        Duck3.setVisible(false);
        summitduck.setVisible(false);
        combobox1.setVisible(false);
        combobox2.setVisible(false);
        combobox3.setVisible(false);
        validate();
    }

    public void addListener() {
        combobox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == combobox1) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String msg = (String) cb.getSelectedItem();
                    switch (msg) {
                        case "Red":
                            Duck1.setIcon(duckRed);
                            break;
                        case "Orange":
                            Duck1.setIcon(duckOrange);
                            break;
                        case "Yellow":
                            Duck1.setIcon(duckYellow);
                            break;
                        case "Green":
                            Duck1.setIcon(duckGreen);
                            break;
                        case "Cyan":
                            Duck1.setIcon(duckCyan);
                            break;
                        case "Blue":
                            Duck1.setIcon(duckBlue);
                            break;
                        case "Purple":
                            Duck1.setIcon(duckPurple);
                            break;
                        default:
                            Duck1.setIcon(duckRed);
                            break;

                    }
                }
            }
        });
        combobox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == combobox2) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String msg = (String) cb.getSelectedItem();
                    switch (msg) {
                        case "Red":
                            Duck2.setIcon(duckRed);
                            break;
                        case "Orange":
                            Duck2.setIcon(duckOrange);
                            break;
                        case "Yellow":
                            Duck2.setIcon(duckYellow);
                            break;
                        case "Green":
                            Duck2.setIcon(duckGreen);
                            break;
                        case "Cyan":
                            Duck2.setIcon(duckCyan);
                            break;
                        case "Blue":
                            Duck2.setIcon(duckBlue);
                            break;
                        case "Purple":
                            Duck2.setIcon(duckPurple);
                            break;
                        default:
                            Duck2.setIcon(duckRed);
                            break;

                    }
                }
            }
        });
        combobox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == combobox3) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String msg = (String) cb.getSelectedItem();
                    switch (msg) {
                        case "Red":
                            Duck3.setIcon(duckRed);
                            break;
                        case "Orange":
                            Duck3.setIcon(duckOrange);
                            break;
                        case "Yellow":
                            Duck3.setIcon(duckYellow);
                            break;
                        case "Green":
                            Duck3.setIcon(duckGreen);
                            break;
                        case "Cyan":
                            Duck3.setIcon(duckCyan);
                            break;
                        case "Blue":
                            Duck3.setIcon(duckBlue);
                            break;
                        case "Purple":
                            Duck3.setIcon(duckPurple);
                            break;
                        default:
                            Duck3.setIcon(duckRed);
                            break;

                    }
                }
            }
        });
        BackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                BR.setVisible(true);
                dispose();
            }
        });
        summit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(JPass.getPassword());
                if (password.equals(PassWord)) {
                    Duck1.setVisible(true);
                    Duck2.setVisible(true);
                    Duck3.setVisible(true);
                    summitduck.setVisible(true);
                    combobox1.setVisible(true);
                    combobox2.setVisible(true);
                    combobox3.setVisible(true);
                    PassLabel.setVisible(false);
                    JPass.setVisible(false);
                    summit.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Password wrong Plz try again!");
                }

            }
        });
        summitduck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int colortrue = 0;
                String msg1 = (String) combobox1.getSelectedItem();
                String msg2 = (String) combobox2.getSelectedItem();
                String msg3 = (String) combobox3.getSelectedItem();
                if (msg1 == "Red") {
                    colortrue++;
                }
                if (msg2 == "Blue") {
                    colortrue++;
                }
                if (msg3 == "Yellow") {
                    colortrue++;
                }
                if (colortrue == 3) {
                    Duck1.setVisible(false);
                    Duck2.setVisible(false);
                    Duck3.setVisible(false);
                    summitduck.setVisible(false);
                    combobox1.setVisible(false);
                    combobox2.setVisible(false);
                    combobox3.setVisible(false);
                    CongratLabel.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Wrong Plz try again!");
                }

            }
        });
    }
}

class Teddypuzzle extends OurFrame {

    private BedRoom BR;
    private JLabel TeddyBearLabel, BackLabel, BackTeddyBearLabel;
    private ImageSet  BacktImg;
    private JRadioButton b1,b2;

    public Teddypuzzle(BedRoom B) {
        super("Teddy");
        BR = B;
        contentpane.setLayout(new BorderLayout());
        contentpane.setBackground(Color.LIGHT_GRAY);

        addcomponent();
    }

    public void addcomponent() {
        setIconImage(new ImageSet("resource/logo.png").getImage());
        BacktImg = new ImageSet("resource/back.png").resize(64, 64);
        drawpane = new JLabel();
        drawpane.setLayout(null);

        TeddyBearLabel = new JLabel(new ImageSet("resource/teddy.png").resize(300, 300));
        TeddyBearLabel.setBounds(500, 300, 300, 300);

        BackTeddyBearLabel = new JLabel(new ImageSet("resource/backteddy.png").resize(300, 300));
        BackTeddyBearLabel.setBounds(500, 300, 300, 300);

        BackLabel = new JLabel(BacktImg);
        BackLabel.setBounds(0, 0, 64, 64);

        addListener();
        drawpane.add(BackLabel);
        drawpane.add(TeddyBearLabel);
        drawpane.add(BackTeddyBearLabel);
        BackTeddyBearLabel.setVisible(false);

        b1 = new JRadioButton("Front");
        b2 = new JRadioButton("Back");
        ButtonGroup group = new ButtonGroup();
        b1.setBounds(1200, 200, 100, 100);
        b1.setOpaque(false);
        b1.setContentAreaFilled(false);
        b1.setBorderPainted(false);
        b1.setSelected(true);
        b1.setFont(new Font("Arial", Font.PLAIN, 28));

        b2.setOpaque(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        b2.setBounds(1200, 300, 100, 100);
        b2.setFont(new Font("Arial", Font.PLAIN, 28));
        group.add(b2);
        group.add(b1);
        drawpane.add(b1);
        drawpane.add(b2);
        
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }

    public void addListener() {
        BackTeddyBearLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                new TeddyZip(BR).setVisible(true);
            }

        });
        BackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                BR.setVisible(true);
                //setVisible(false);
                dispose();
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeddyBearLabel.setVisible(true);
                BackTeddyBearLabel.setVisible(false);
            }

        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeddyBearLabel.setVisible(false);
                BackTeddyBearLabel.setVisible(true);
            }

        });

    }

}

class TeddyZip extends OurFrame {

    private BedRoom BR;
    private JLabel BackTeddyBearLabel, ZipCloseLabel, ZipOpenLabel, PassLabel, BackLabel;
    private int x, y, x2, y2;

    public TeddyZip(BedRoom B) {
        super("Teddy Puzzle(Back)");
        BR = B;
        contentpane.setLayout(new BorderLayout());
        contentpane.setBackground(Color.DARK_GRAY);
        addcomponent();

    }

    public void addcomponent() {
        setIconImage(new ImageSet("resource/logo.png").getImage());
        drawpane = new JLabel();
        drawpane.setLayout(null);

        BackLabel = new JLabel(new ImageSet("resource/back.png").resize(64, 64));
        BackLabel.setBounds(0, 0, 64, 64);

        BackTeddyBearLabel = new JLabel(new ImageSet("resource/backteddy.png").resize(600, 600));
        BackTeddyBearLabel.setBounds(300, 100, 600, 600);

        ZipCloseLabel = new JLabel(new ImageSet("resource/zipclose.png").resize(100, 400));
        ZipCloseLabel.setBounds(550, 200, 100, 400);

        ZipOpenLabel = new JLabel(new ImageSet("resource/zipopen.png").resize(100, 400));
        ZipOpenLabel.setBounds(550, 200, 100, 400);

        PassLabel = new JLabel(new ImageSet("resource/pass.png").resize(200, 200));
        PassLabel.setBounds(500, 200, 200, 200);

        drawpane.add(PassLabel);
        drawpane.add(ZipCloseLabel);
        drawpane.add(ZipOpenLabel);
        drawpane.add(BackTeddyBearLabel);
        drawpane.add(BackLabel);

        PassLabel.setVisible(false);
        ZipOpenLabel.setVisible(false);

        addListener();

        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }

    public void addListener() {
        BackTeddyBearLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                if (y2 - y >= 250 && x2 - x <= 50) {
                    ZipCloseLabel.setVisible(false);
                    ZipOpenLabel.setVisible(true);
                    PassLabel.setVisible(true);
                }
            }
        });
        BackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                BR.setVisible(true);
                //setVisible(false);
                dispose();
            }
        });

    }

}


