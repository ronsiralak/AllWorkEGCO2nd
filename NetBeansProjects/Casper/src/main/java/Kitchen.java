
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
public class Kitchen extends OurFrame {

    private Kitchen K = this;
    private ImageSet backgroundImg;
    private JLabel RefrigeratorLabel, TableLabel, SinkLabel, GotoCenterLabel, GotoButtomLabel, CraftLabel;
    private boolean unlock = false, fixed = false;

    public Kitchen() {
        super("Kitchen");
        setIconImage(new ImageSet("resource/logo.png").getImage());
        pt = new Player("PornChai");
        contentpane.setLayout(new BorderLayout());
        addcomponent();

    }

    public void addcomponent() {
        backgroundImg = new ImageSet("resource/kitchenBG.jpg").resize(1366, 668);

        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);

        RefrigeratorLabel = new JLabel(new ImageSet("resource/refrigerator.png").resize(125, 175));
        RefrigeratorLabel.setBounds(600, 0, 125, 175);

        GotoCenterLabel = new JLabel(new ImageSet("resource/leftarrow.png").resize(150, 100));
        GotoCenterLabel.setBounds(0, 200, 100, 100);

        TableLabel = new JLabel(new ImageSet("resource/table.png").resize(150, 150));
        TableLabel.setBounds(600, 200, 150, 150);

        SinkLabel = new JLabel(new ImageSet("resource/sink.png").resize(100, 150));
        SinkLabel.setBounds(1100, 200, 150, 150);

        GotoButtomLabel = new JLabel(new ImageSet("resource/downarrow.png").resize(150, 100));
        GotoButtomLabel.setBounds(600, 500, 100, 100);

        CraftLabel = new JLabel(new ImageSet("resource/craft.png").resize(100, 100));
        CraftLabel.setBounds(0, 500, 100, 100);

        addListener();
        drawpane.add(GotoCenterLabel);
        drawpane.add(RefrigeratorLabel);
        drawpane.add(TableLabel);
        drawpane.add(SinkLabel);
        drawpane.add(GotoButtomLabel);
        drawpane.add(CraftLabel);

        contentpane.add(drawpane, BorderLayout.CENTER);
        updatePlayerpane();
        validate();

    }

    public void addListener() {
        RefrigeratorLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (unlock) {
                    setVisible(false);
                    new Refrigeratorpuzzle(K).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "It is locked!!");
                }

            }
        });
        SinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                new Sinkpuzzle(K).setVisible(true);

            }
        });
        CraftLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                new Craftpuzzle(K).setVisible(true);

            }
        });
        GotoCenterLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                //new TV().setVisible(true); 
            }
        });
        GotoButtomLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                //new Toliet().setVisible(true); 
            }
        });

    }

    public boolean getfixed() {
        return fixed;
    }

    public void setfixed() {
        fixed = true;
    }

    public boolean getunlock() {
        return unlock;
    }

    public void setunlock() {
        unlock = true;
    }

   /* public static void main(String args[]) {
        new Kitchen();

    }*/
}

class Refrigeratorpuzzle extends OurFrame {

    private Kitchen K;
    private JLabel topRefrigeLabel, bottomRefrigeLabel,
            opentopRefrigeIronLabel,
            opentopRefrigeLabel, BackLabel;

    public Refrigeratorpuzzle(Kitchen k) {
        super("Refrigerator");
        K = k;
        pt = k.getPlayer();

        contentpane.setLayout(new BorderLayout());
        contentpane.setBackground(Color.LIGHT_GRAY);

        addcomponent();
    }

    public void addcomponent() {

        topRefrigeLabel = new JLabel(new ImageSet("resource/toprefrige.png").resize(200, 200));
        topRefrigeLabel.setBounds(500, 50, 200, 200);

        bottomRefrigeLabel = new JLabel(new ImageSet("resource/bottomrefrige.png").resize(200, 250));
        bottomRefrigeLabel.setBounds(500, 250, 200, 250);

        opentopRefrigeLabel = new JLabel(new ImageSet("resource/opentoprefrige.png").resize(200, 200));
        opentopRefrigeLabel.setBounds(500, 50, 200, 200);
        opentopRefrigeLabel.setVisible(false);

        opentopRefrigeIronLabel = new JLabel(new ImageSet("resource/opentoprefrigeIron.png").resize(200, 200));
        opentopRefrigeIronLabel.setBounds(500, 50, 200, 200);
        opentopRefrigeIronLabel.setVisible(false);

        BackLabel = new JLabel(new ImageSet("resource/back.png").resize(64, 64));
        BackLabel.setBounds(0, 0, 64, 64);

        addListener();
        drawpane = new JLabel();

        drawpane.setLayout(null);
        contentpane.add(drawpane, BorderLayout.CENTER);
        drawpane.add(topRefrigeLabel);
        drawpane.add(bottomRefrigeLabel);
        drawpane.add(opentopRefrigeLabel);
        drawpane.add(opentopRefrigeIronLabel);
        drawpane.add(BackLabel);
        updatePlayerpane();
    }

    public void addListener() {
        topRefrigeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                opentopRefrigeIronLabel.setVisible(true);
                topRefrigeLabel.setVisible(false);

            }
        });
        opentopRefrigeIronLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Get 3 Irons");
                opentopRefrigeLabel.setVisible(true);
                opentopRefrigeIronLabel.setVisible(false);
                pt.getItem(3).setamount(3);
            }
        });
        BackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                K.setVisible(true);
                dispose();
            }
        });
    }
}

class Sinkpuzzle extends OurFrame {

    private Kitchen K;
    private JLabel BrokeSinkLabel, FixSinkLabel, BackLabel;
    private boolean usetape = false;

    public Sinkpuzzle(Kitchen k) {
        super("Sink");
        K = k;
        pt = k.getPlayer();

        contentpane.setLayout(new BorderLayout());
        contentpane.setBackground(Color.LIGHT_GRAY);

        addcomponent();
    }

    public void addcomponent() {
        BrokeSinkLabel = new JLabel(new ImageSet("resource/BrokeSink.png").resize(500, 500));
        BrokeSinkLabel.setBounds(400, 50, 500, 500);
        BrokeSinkLabel.setVisible(false);

        FixSinkLabel = new JLabel(new ImageSet("resource/FixSink.png").resize(500, 500));
        FixSinkLabel.setBounds(400, 50, 500, 500);
        FixSinkLabel.setVisible(false);

        BackLabel = new JLabel(new ImageSet("resource/back.png").resize(64, 64));
        BackLabel.setBounds(0, 0, 64, 64);

        addListener();
        checkfixed();
        drawpane = new JLabel();

        drawpane.setLayout(null);
        drawpane.add(BrokeSinkLabel);
        drawpane.add(FixSinkLabel);
        drawpane.add(BackLabel);
        contentpane.add(drawpane, BorderLayout.CENTER);
        updatePlayerpane();
    }

    public void checkfixed() {
        if (K.getfixed()) {
            BrokeSinkLabel.setVisible(false);
            FixSinkLabel.setVisible(true);
        } else {
            BrokeSinkLabel.setVisible(true);
            FixSinkLabel.setVisible(false);
        }
    }

    public void addListener() {
        BrokeSinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (usetape) {
                    fixed();
                    BrokeSinkLabel.setVisible(false);
                    FixSinkLabel.setVisible(true);
                    K.setunlock();

                }

            }
        });
        pt.getRadio(1).addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    usetape = true;
                } else {
                    usetape = false;
                }
            }

        });
        BackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                K.setVisible(true);
                dispose();
            }
        });
    }

    public void fixed() {
        K.setfixed();
    }

}

class Craftpuzzle extends OurFrame {

    private Kitchen K;
    private JLabel arrowLabel,
            craft1Label, craft2Label, craft3Label,
            craft4Label, craft5Label, craft6Label,
            craft7Label, craft8Label, craft9Label,
            craftedLabel, woodLabel1, woodLabel2,
            ironLabel1, ironLabel2, ironLabel3,
            AxeLabel, BackLabel;

    public Craftpuzzle(Kitchen k) {
        super("Crafting Table");
        pt = k.getPlayer();
        K = k;
        contentpane.setLayout(new BorderLayout());
        contentpane.setBackground(Color.LIGHT_GRAY);

        addcomponent();
    }

    public void addcomponent() {
        BackLabel = new JLabel(new ImageSet("resource/back.png").resize(64, 64));
        BackLabel.setBounds(0, 0, 64, 64);

        craft1Label = new JLabel(new ImageSet("resource/pointcrafttable.png").resize(100, 100));
        craft1Label.setBounds(400, 50, 100, 100);

        craft2Label = new JLabel(new ImageSet("resource/pointcrafttable.png").resize(100, 100));
        craft2Label.setBounds(400, 150, 100, 100);

        craft3Label = new JLabel(new ImageSet("resource/pointcrafttable.png").resize(100, 100));
        craft3Label.setBounds(400, 250, 100, 100);

        craft4Label = new JLabel(new ImageSet("resource/pointcrafttable.png").resize(100, 100));
        craft4Label.setBounds(500, 50, 100, 100);

        craft5Label = new JLabel(new ImageSet("resource/pointcrafttable.png").resize(100, 100));
        craft5Label.setBounds(500, 150, 100, 100);

        craft6Label = new JLabel(new ImageSet("resource/pointcrafttable.png").resize(100, 100));
        craft6Label.setBounds(500, 250, 100, 100);

        craft7Label = new JLabel(new ImageSet("resource/pointcrafttable.png").resize(100, 100));
        craft7Label.setBounds(600, 50, 100, 100);

        craft8Label = new JLabel(new ImageSet("resource/pointcrafttable.png").resize(100, 100));
        craft8Label.setBounds(600, 150, 100, 100);

        craft9Label = new JLabel(new ImageSet("resource/pointcrafttable.png").resize(100, 100));
        craft9Label.setBounds(600, 250, 100, 100);

        craftedLabel = new JLabel(new ImageSet("resource/pointcrafttable.png").resize(100, 100));
        craft1Label.setBounds(400, 50, 100, 100);

        arrowLabel = new JLabel(new ImageSet("resource/craftarrow.png").resize(100, 100));
        arrowLabel.setBounds(750, 150, 100, 100);

        craftedLabel = new JLabel(new ImageSet("resource/pointcrafttable.png").resize(150, 150));
        craftedLabel.setBounds(900, 125, 150, 150);

        AxeLabel = new JLabel(new ImageSet("resource/AXE.png").resize(150, 150));
        AxeLabel.setBounds(900, 125, 150, 150);
        AxeLabel.setVisible(false);

        addListener();

        drawpane = new JLabel();

        drawpane.setLayout(null);
        drawpane.add(craft1Label);
        drawpane.add(craft2Label);
        drawpane.add(craft3Label);
        drawpane.add(craft4Label);
        drawpane.add(craft5Label);
        drawpane.add(craft6Label);
        drawpane.add(craft7Label);
        drawpane.add(craft8Label);
        drawpane.add(craft9Label);
        drawpane.add(arrowLabel);
        drawpane.add(craftedLabel);
        drawpane.add(AxeLabel);
        drawpane.add(BackLabel);
        checkmatrial();
        contentpane.add(drawpane, BorderLayout.CENTER);
        updatePlayerpane();
    }

    public void checkmatrial() {
        if (pt.getItem(2).getamount() == 2) {
            woodLabel1 = new JLabel(new ImageSet("resource/wood.png").resize(100, 100));
            woodLabel1.setBounds(500, 150, 100, 100);

            woodLabel2 = new JLabel(new ImageSet("resource/wood.png").resize(100, 100));
            woodLabel2.setBounds(500, 250, 100, 100);

            drawpane.add(woodLabel1);
            drawpane.add(woodLabel2);

            craft5Label.setVisible(false);
            craft6Label.setVisible(false);

        }
        if (pt.getItem(3).getamount() == 3) {
            ironLabel1 = new JLabel(new ImageSet("resource/iron.png").resize(100, 100));
            ironLabel1.setBounds(400, 50, 100, 100);

            ironLabel2 = new JLabel(new ImageSet("resource/iron.png").resize(100, 100));
            ironLabel2.setBounds(400, 150, 100, 100);

            ironLabel3 = new JLabel(new ImageSet("resource/iron.png").resize(100, 100));
            ironLabel3.setBounds(500, 50, 100, 100);

            drawpane.add(ironLabel1);
            drawpane.add(ironLabel2);
            drawpane.add(ironLabel3);

            craft1Label.setVisible(false);
            craft2Label.setVisible(false);
            craft4Label.setVisible(false);

        }
        if (pt.getItem(2).getamount() == 2 && pt.getItem(3).getamount() == 3) {
            AxeLabel.setVisible(true);
            craftedLabel.setVisible(false);
        }
    }

    public void addListener() {
        AxeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pt.getItem(2).setamount(0);
                pt.getItem(3).setamount(0);
                pt.getItem(4).setamount(1);
                JOptionPane.showMessageDialog(null,
                        "You get AXE");
                craft1Label.setVisible(true);
                craft2Label.setVisible(true);
                craft4Label.setVisible(true);
                craft5Label.setVisible(true);
                craft6Label.setVisible(true);
                craftedLabel.setVisible(true);
                AxeLabel.setVisible(false);
                ironLabel1.setVisible(false);
                ironLabel2.setVisible(false);
                ironLabel3.setVisible(false);
                woodLabel1.setVisible(false);
                woodLabel2.setVisible(false);

            }
        });
        BackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                K.setVisible(true);
                dispose();
            }
        });
    }
}
