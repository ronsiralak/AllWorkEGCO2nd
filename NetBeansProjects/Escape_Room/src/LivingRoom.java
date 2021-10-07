
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LivingRoom extends OurFrame {

    private ImageSet backgroundImg, MusicBoxImg, TVImg, PillowImg, DrawercloseImg, DraweropenImg, DoorExitImg,
            chainopenImg, chaincloseImg, TapeImg, woodImg, RightImg;
    private JLabel MusicBoxLabel, TVLabel, PillowLabel, TapeLabel,
            KitchenRoomLabel, DrawerLabel, WoodLabel, DoorExitLabel, BedRoomLabel;
    private boolean Haveaxe, openDrawer, bedroomchain, pickwood;
    private int Xx, Yy;
    private Musicpuzzle Pianopuzzle;
    private TV TVpuzzle;
    private DoorExit Doorexit;

    public LivingRoom(Player p) {

        super("Living room", p);
        player = p;
        
        contentpane.setLayout(new BorderLayout());

        Haveaxe = false;
        bedroomchain = true;
        openDrawer = false;
        pickwood = false;
        addcomponent();

    }

    public void addcomponent() {
        //set up obj ต่างๆ ในห้อง

        //import รูปจากFile
        backgroundImg = new ImageSet("resource/LivingRoom/background/BGNormal.png").resize(1366, 618);
        MusicBoxImg = new ImageSet("resource/LivingRoom/object/Musicbox.png").resize(120, 52);
        TVImg = new ImageSet("resource/LivingRoom/object/TV.jpeg").resize(338, 121);
        PillowImg = new ImageSet("resource/LivingRoom/object/pilow.png").resize(124, 62);
        DrawercloseImg = new ImageSet("resource/LivingRoom/object/drawerclose.png").resize(124, 57);
        DraweropenImg = new ImageSet("resource/LivingRoom/object/draweropen.png").resize(300, 100);
        DoorExitImg = new ImageSet("resource/LivingRoom/object/exit.jpeg");
        chainopenImg = new ImageSet("resource/LivingRoom/object/Nochain.png").resize(35, 82);
        chaincloseImg = new ImageSet("resource/LivingRoom/object/chain.jpeg");
        TapeImg = new ImageSet("resource/LivingRoom/object/tape.png").resize(50, 50);
        woodImg = new ImageSet("resource/LivingRoom/object/wood.png").resize(64, 64);
        RightImg = new ImageSet("resource/Arrow/Right.png").resize(64, 64);

        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);

        KitchenRoomLabel = new JLabel(RightImg);
        KitchenRoomLabel.setBounds(1280, 0, 64, 64);
        KitchenRoomLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        MusicBoxLabel = new JLabel(MusicBoxImg);
        MusicBoxLabel.setBounds(632, 310, 120, 52);
        MusicBoxLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        TVLabel = new JLabel(TVImg);
        TVLabel.setBounds(488, 110, 383, 111);
        TVLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        PillowLabel = new JLabel(PillowImg);
        PillowLabel.setBounds(290, 270, 124, 62);
        PillowLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        TapeLabel = new JLabel(TapeImg); //  TabeLabel.setBounds(290, 270, 80, 80);
        TapeLabel.setBounds(300, 270, 50, 50);
        TapeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        DrawerLabel = new JLabel(DrawercloseImg);
        DrawerLabel.setBounds(115, 466, 124, 57);
        DrawerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //DrawerLabel = new JLabel(DraweropenImg);
        //DrawerLabel.setBounds(15, 466, 300, 100);

        WoodLabel = new JLabel(woodImg);
        WoodLabel.setBounds(130, 450, 80, 80);
        WoodLabel.setVisible(false);
        WoodLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        DoorExitLabel = new JLabel(DoorExitImg);
        DoorExitLabel.setBounds(1115, 170, 50, 99);
        DoorExitLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        BedRoomLabel = new JLabel(chaincloseImg);
        BedRoomLabel.setBounds(165, 139, 35, 82);
        BedRoomLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        /* BedRoomLabel = new JLabel(chainopenImg);
        BedRoomLabel.setBounds(165, 139, 35, 82);*/
        addListener();
        addPuzzle();
        drawpane.add(WoodLabel);
        drawpane.add(MusicBoxLabel);
        drawpane.add(TVLabel);
        drawpane.add(PillowLabel);
        drawpane.add(TapeLabel);
        drawpane.add(KitchenRoomLabel);
        drawpane.add(DrawerLabel);
        drawpane.add(DoorExitLabel);
        drawpane.add(BedRoomLabel);

        contentpane.add(drawpane, BorderLayout.CENTER);
        //updatePlayerpane();
        //สร้าง panel สำหรับ player ข้างบน

    }

    public void addListener() {
        DrawerLabel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                if (openDrawer) {
                    DrawerLabel.setIcon(DraweropenImg);
                    DrawerLabel.setBounds(15, 466, 300, 100);
                    WoodLabel.setVisible(true);

                    openDrawer = false;
                    pickwood = true;

                } else if (!openDrawer && !pickwood) {
                    player.getLockSound().playOnce();
                    JOptionPane.showMessageDialog(null,
                            "Lock ");
                }
            }

        });
        WoodLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                WoodLabel.setIcon(null);
                player.lostitem(0);
                player.pickItem(2);

            }

        });
        PillowLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                PillowLabel.setLocation(330, 300);
            }

        });

        MusicBoxLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                player.getRoomSound().stop();
                player.getOpenpopupSound().playOnce();
                Pianopuzzle.Open();

            }

        });
        TVLabel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                setVisible(false);
                player.getOpenpopupSound().playOnce();
                TVpuzzle.Open();

            }

        });
        DoorExitLabel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                setVisible(false);
                player.getOpenpopupSound().playOnce();
                Doorexit.Open();
            }

        });

        TapeLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                TapeLabel.setIcon(null);
                player.pickItem(1);

            }

        });
        KitchenRoomLabel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                setVisible(false);
                player.getOpenRoomSound().playOnce();
                player.clearradiofocus();
                player.getKitchen().Open();

            }

        });
        BedRoomLabel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                if (!bedroomchain) {
                    setVisible(false);
                    player.getOpenRoomSound().playOnce();
                    player.getBedRoom().Open();

                } else if (Haveaxe) {
                    bedroomchain = false;
                    //setVisible(false);
                    Haveaxe = false;
                    player.lostitem(4);
                    /*player.clearradiofocus();
                player.getBedRoom().Open();*/
                    BedRoomLabel.setIcon(chainopenImg);

                } else {
                    player.getLockSound().playOnce();
                    JOptionPane.showMessageDialog(null,
                            "Door is Lock");

                }

            }

        });
        player.getRadio(0).addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    if (player.getItem(0).getamount() == 1) {
                        openDrawer = true;
                    } else {
                        openDrawer = false;
                    }

                } else {
                    openDrawer = false;

                }

            }

        });
        player.getRadio(4).addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    if (player.getItem(4).getamount() == 1) {
                        Haveaxe = true;
                    } else {
                        Haveaxe = false;
                    }

                } else {
                    Haveaxe = false;

                }

            }

        });

    }

    public void addPuzzle() {

        Pianopuzzle = new Musicpuzzle(player);
        TVpuzzle = new TV(player);
        Doorexit = new DoorExit(player);
    }

    public static void main(String args[]) {

        Player Player1 = new Player("Pete");

        Player1.getLivingRoom().Open();
        Player1.getRoomSound().playLoop();

    }
}
// Auxiliary class to resize image

class Musicpuzzle extends OurFrame {

    //private LivingRoom LVR;
    private JLabel MusicpianoLabel, BackLabel, ButtonLabel, NoteArea, KeypuzzleLabel, NoteSheetLabel;
    private ImageSet backgroundImg, MusicBoxImg, BacktImg, KeypuzzleImg, MusicunLockpuzzleImg, ButtonImg, NotesheetImg;
    private SoundEffect[] pianosound;
    private SoundEffect twinklesound;
    private String note = "";
    private boolean firsttimefocus, unlock, pickup;

    public Musicpuzzle(Player P) {
        super("Puzzle Music", P);
        //player = P;

        firsttimefocus = false;
        pickup = false;
        contentpane.setLayout(new BorderLayout());
        contentpane.setBackground(Color.DARK_GRAY);

        addcomponent();
    }

    public void addcomponent() {
        Font f = new Font("SanSerif", Font.BOLD, 20);
        backgroundImg = new ImageSet("resource/LivingRoom/background/BGpopup.png").resize(1366, 668);
        MusicBoxImg = new ImageSet("resource/LivingRoom/pop up/pianolock.png").resize(800, 568);
        BacktImg = new ImageSet("resource/Arrow/Left.png");
        ButtonImg = new ImageSet("resource/LivingRoom/pop up/buttom.png").resize(64, 64);
        MusicunLockpuzzleImg = new ImageSet("resource/LivingRoom/pop up/pianoopen.png").resize(800, 568);
        KeypuzzleImg = new ImageSet("resource/LivingRoom/object/Key.png").resize(129, 273);
        NotesheetImg = new ImageSet("resource/LivingRoom/pop up/Notesheet.png").resize(200, 200);

        drawpane = new JLabel();
        drawpane.setLayout(null);
        drawpane.setIcon(backgroundImg);

        MusicpianoLabel = new JLabel(MusicBoxImg);
        MusicpianoLabel.setBounds(300, 0, 800, 568);
        

        BackLabel = new JLabel(BacktImg);
        BackLabel.setBounds(0, 0, 64, 64);
        BackLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ButtonLabel = new JLabel(ButtonImg);
        ButtonLabel.setBounds(1100, 200, 64, 64);
        ButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        KeypuzzleLabel = new JLabel(KeypuzzleImg);
        KeypuzzleLabel.setBounds(250, 50, 129, 273);
        KeypuzzleLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        KeypuzzleLabel.setVisible(false);
        

        NoteArea = new JLabel();
        NoteArea.setForeground(Color.BLACK);
        NoteArea.setFont(f);
        NoteArea.setBounds(150, 100, 200, 200);

        NoteSheetLabel = new JLabel(NotesheetImg);
        NoteSheetLabel.setBounds(120, 110, 200, 200);
        NoteSheetLabel.add(NoteArea);

        addListener();
        addmusic();

        MusicpianoLabel.add(KeypuzzleLabel);

        drawpane.add(MusicpianoLabel);
        drawpane.add(BackLabel);
        drawpane.add(NoteArea);
        drawpane.add(ButtonLabel);
        drawpane.add(NoteSheetLabel);
        //drawpane.add(KeypuzzleLabel);

        contentpane.add(drawpane, BorderLayout.CENTER);

        validate();

    }

    public void addListener() {
        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                //System.out.print("hello");
                if (e.getKeyCode() == KeyEvent.VK_T) {

                    note += "T";
                    pianosound[0].stop();
                    pianosound[0].playOnce();

                }

                if (e.getKeyCode() == KeyEvent.VK_O) {
                    note += "O";
                    pianosound[4].stop();
                    pianosound[4].playOnce();
                }
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    note += "P";
                    pianosound[5].stop();
                    pianosound[5].playOnce();
                }
                if (e.getKeyCode() == KeyEvent.VK_I) {
                    note += "I";
                    pianosound[3].stop();
                    pianosound[3].playOnce();
                }
                if (e.getKeyCode() == KeyEvent.VK_U) {
                    note += "U";
                    pianosound[2].stop();
                    pianosound[2].playOnce();
                }
                if (e.getKeyCode() == KeyEvent.VK_Y) {
                    note += "Y";
                    pianosound[1].stop();
                    pianosound[1].playOnce();
                }
                if (e.getKeyCode() == KeyEvent.VK_K) {
                    note += "K";
                    pianosound[6].stop();
                    pianosound[6].playOnce();

                }
                if (e.getKeyCode() == KeyEvent.VK_L) {
                    note += "L";
                    pianosound[7].stop();
                    pianosound[7].playOnce();

                }
                Notepuzzle();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
        ButtonLabel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                if (!firsttimefocus) {
                    setFocusable(true);
                    firsttimefocus = true;
                }
                JOptionPane.showMessageDialog(null,
                        "Use Keyboard to play piano");
                requestFocusInWindow(true);

            }

        });

        BackLabel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                setVisible(false);
                twinklesound.stop();
                player.getRoomSound().playLoop();
                player.getLivingRoom().Open();

            }

        });

        KeypuzzleLabel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                if (!pickup && unlock) {

                    player.pickItem(0);
                    KeypuzzleLabel.setIcon(null);
                    pickup = true;
                    unlock = false;
                    validate();
                }

            }

        });

    }

    public void addmusic() {

        twinklesound = new SoundEffect("music/SoundEffect/Piano/twinkle.wav");
        pianosound = new SoundEffect[8];
        pianosound[0] = new SoundEffect("music/SoundEffect/Piano/Do.wav");
        pianosound[1] = new SoundEffect("music/SoundEffect/Piano/Re.wav");
        pianosound[2] = new SoundEffect("music/SoundEffect/Piano/Mi.wav");
        pianosound[3] = new SoundEffect("music/SoundEffect/Piano/Fa.wav");
        pianosound[4] = new SoundEffect("music/SoundEffect/Piano/Sol.wav");
        pianosound[5] = new SoundEffect("music/SoundEffect/Piano/La.wav");
        pianosound[6] = new SoundEffect("music/SoundEffect/Piano/SI_2.wav");
        pianosound[7] = new SoundEffect("music/SoundEffect/Piano/Do octave.wav");
    }

    private void Notepuzzle() {
        NoteArea.setText(note);
        if (note.length() == 7) {
            if (note.equals("TTOOPPO")) {
                if (!pickup) {
                    MusicpianoLabel.setIcon(MusicunLockpuzzleImg);
                    KeypuzzleLabel.setVisible(true);
                }

                twinklesound.stop();
                twinklesound.playOnce();

                unlock = true;

                note = "";
            } else {
                //เพิ่มรูปให้เป็นสีแดงว่าไม่ผ่าน
                note = "";
            }

        }

    }

}

class TV extends OurFrame {

    private JLabel TVLabel, TVnextLabel, TVbackLabel, TVpicture, BackLabel;
    private int ChannelPicture = 0;
    private ImageSet[] picture;
    private ImageSet BackImg, backgroundImg, LeftImg, RightImg, TVImg;

    public TV(Player p) {
        super("TV Channel", p);

        contentpane.setLayout(new BorderLayout());
        contentpane.setBackground(Color.DARK_GRAY);
        addcomponent();

    }

    public void addcomponent() {

        picture = new ImageSet[3];
        picture[0] = new ImageSet("resource/LivingRoom/pop up/TVchannel1.jpeg").resize(410, 185);
        picture[1] = new ImageSet("resource/LivingRoom/pop up/TVchannel2.jpeg").resize(410, 185);
        picture[2] = new ImageSet("resource/LivingRoom/pop up/TVchannel3.png").resize(410, 185);
        BackImg = new ImageSet("resource/Arrow/Down.png").resize(64, 64);
        backgroundImg = new ImageSet("resource/LivingRoom/background/BGpopup.png").resize(1366, 668);
        LeftImg = new ImageSet("resource/Arrow/Left.png").resize(64, 64);
        RightImg = new ImageSet("resource/Arrow/Right.png").resize(64, 64);
        TVImg = new ImageSet("resource/LivingRoom/pop up/TV.png").resize(800, 468);

        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);

        TVpicture = new JLabel(picture[0]);
        TVpicture.setBounds(195, 140, 410,185);

        TVLabel = new JLabel(TVImg);
        TVLabel.setBounds(300, 0, 800, 468);
        TVLabel.add(TVpicture);

        TVnextLabel = new JLabel(RightImg);
        TVnextLabel.setBounds(1100, 200, 64, 64);
        TVnextLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        TVbackLabel = new JLabel(LeftImg);
        TVbackLabel.setBounds(200, 200, 64, 64);
        TVbackLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        BackLabel = new JLabel(BackImg);
        BackLabel.setBounds(680, 490, 64, 64);
        BackLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addListener();
        drawpane.add(TVLabel);
        drawpane.add(TVnextLabel);
        drawpane.add(TVbackLabel);
        drawpane.add(BackLabel);
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }

    public void addListener() {
        TVbackLabel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                if (ChannelPicture > 0) {
                    --ChannelPicture;
                    TVpicture.setIcon(picture[ChannelPicture]);

                }

            }

        });

        TVnextLabel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                if (ChannelPicture < 2) {
                    ++ChannelPicture;
                    TVpicture.setIcon(picture[ChannelPicture]);

                }
            }

        });

        BackLabel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                player.getLivingRoom().Open();
                setVisible(false);
            }

        });

    }

}
