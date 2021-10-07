
import java.awt.*;
import java.awt.event.*;
import java.util.Optional;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nearu
 */
public class DoorExit extends OurFrame {

    private String Password = "", temp = "";
    private JLabel PasswordLabel, EnterLabel, UseKeyboardLabel, EnterFieldLabel, BackLabel;
    private ImageSet EnterImg, UseImg, EnterFieldImg, backgroundImg, BackImg;
    private boolean firsttimefocus;

    public DoorExit(Player p) {
        super("DoorExit", p);
        contentpane.setLayout(new BorderLayout());
        addcomponent();
    }

    public void addcomponent() {

        UseImg = new ImageSet("resource/LivingRoom/object/Enter.png").resize(64, 64);
        EnterImg = new ImageSet("resource/LivingRoom/object/use.png").resize(64, 64);
        EnterFieldImg = new ImageSet("resource/LivingRoom/pop up/Exitpopup.png").resize(800, 568);
        backgroundImg = new ImageSet("resource/LivingRoom/background/BGpopup.png").resize(1366, 668);
        BackImg = new ImageSet("resource/Arrow/Left.png").resize(64, 64);

        drawpane = new JLabel();
        drawpane.setLayout(null);
        drawpane.setIcon(backgroundImg);

        Font f = new Font("SanSerif", Font.BOLD, 50);
        PasswordLabel = new JLabel();
        PasswordLabel.setForeground(Color.WHITE);
        PasswordLabel.setFont(f);
        PasswordLabel.setBounds(650, 100, 200, 50);

        EnterLabel = new JLabel(EnterImg);
        EnterLabel.setBounds(800, 300, 64, 64);
        EnterLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        EnterFieldLabel = new JLabel(EnterFieldImg);
        EnterFieldLabel.setBounds(300, 0, 800, 568);

        UseKeyboardLabel = new JLabel(UseImg);
        UseKeyboardLabel.setBounds(1030, 300, 64, 64);
        UseKeyboardLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        BackLabel = new JLabel(BackImg);
        BackLabel.setBounds(0, 0, 64, 64);
        BackLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addListener();

        drawpane.add(PasswordLabel);
        drawpane.add(EnterLabel);
        drawpane.add(UseKeyboardLabel);
        drawpane.add(EnterFieldLabel);
        drawpane.add(BackLabel);
        contentpane.add(drawpane, BorderLayout.CENTER);

    }

    public void CheckPassword() {
        if (!Password.isEmpty()) {
            if (Password.equals("29012")) {
                player.getRoomSound().stop();
                new Endgame(new Player("pete")).setVisible(true);
                dispose();

            } else {
                JOptionPane.showMessageDialog(null,
                        "Wrong password");
                Password = "";
                PasswordLabel.setText(Password);

            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Enter Password");
        }

    }

    public void addListener() {
        BackLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                player.getLivingRoom().Open();
            }

        });
        EnterLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                CheckPassword();
            }

        });
        UseKeyboardLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (!firsttimefocus) {
                    setFocusable(true);
                    firsttimefocus = true;
                }
                JOptionPane.showMessageDialog(null,
                        "Use Numpad to Enter password ");
                requestFocusInWindow(true);
            }

        });
        this.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {

                if (Password.length() < 5) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_NUMPAD0:
                            Password += 0;
                            break;
                        case KeyEvent.VK_NUMPAD1:
                            Password += 1;
                            break;
                        case KeyEvent.VK_NUMPAD2:
                            Password += 2;
                            break;
                        case KeyEvent.VK_NUMPAD3:
                            Password += 3;
                            break;
                        case KeyEvent.VK_NUMPAD4:
                            Password += 4;
                            break;
                        case KeyEvent.VK_NUMPAD5:
                            Password += 5;
                            break;
                        case KeyEvent.VK_NUMPAD6:
                            Password += 6;
                            break;
                        case KeyEvent.VK_NUMPAD7:
                            Password += 7;
                            break;
                        case KeyEvent.VK_NUMPAD8:
                            Password += 8;
                            break;
                        case KeyEvent.VK_NUMPAD9:
                            Password += 9;
                            break;
                    }
                    PasswordLabel.setText(Password);
                }
                //deleted Key

                if (e.getKeyChar() == KeyEvent.VK_DELETE) {
                    Password = Optional.ofNullable(Password)
                            .filter(s -> !s.isEmpty())
                            .map(s -> s.substring(0, s.length() - 1))
                            .orElse(Password);
                    PasswordLabel.setText(Password);
                }

            }

        });

    }

    /* public static void main(String args[]) {
        //new DoorExit(new Player("pete")).Open();
       // new Endgame(new Player("pete")).setVisible(true);

    }*/
}

class Endgame extends OurFrame {

    private JLabel QuitLabel, CreditLabel;
    private ImageSet QuitImg, CreditImg, backgroundImg;

    public Endgame(Player p) {
        super("End", p);
        contentpane.setLayout(new BorderLayout());

        addcomponent();
    }

    public void addcomponent() {
        backgroundImg = new ImageSet("resource/menu/Endgame/BG.png").resize(1366, 768);
        QuitImg = new ImageSet("resource/menu/Endgame/quit.png").resize(64, 64);
        CreditImg = new ImageSet("resource/menu/Endgame/credit.png").resize(130, 100);;

        drawpane = new JLabel();
        drawpane.setLayout(null);
        drawpane.setIcon(backgroundImg);

        QuitLabel = new JLabel(QuitImg);
        QuitLabel.setBounds(405, 537, 64, 64);
        QuitLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        CreditLabel = new JLabel(CreditImg);
        CreditLabel.setBounds(150, 50, 130, 100);
        CreditLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addListener();
        drawpane.add(QuitLabel);
        drawpane.add(CreditLabel);

        contentpane.add(drawpane, BorderLayout.CENTER);

    }

    public void addListener() {
        QuitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        CreditLabel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                setVisible(false);
                new Credit().setVisible(true);
            }
        });

    }

}
