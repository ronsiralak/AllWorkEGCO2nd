
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class FourCharactersFrame extends JFrame implements KeyListener {

    private JLabel contentpane;
    private MyLabel BirdLabel, HorseLabel, targetLabel;
    // adjust frame's size as you want
    private int frameWidth = 800, frameHeight = 500;

    public FourCharactersFrame() {
        setTitle("Bird moves");
        setBounds(50, 50, frameWidth, frameHeight);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // set background image by using JLabel as contentpane
        setContentPane(contentpane = new JLabel());
        MyImageIcon background = new MyImageIcon("resources/background.jpg");
        contentpane.setIcon(background.resize(frameWidth, frameHeight));
        contentpane.setLayout(null);

        BirdLabel = new MyLabel("resources/bird.png", "resources/plane.png");
        BirdLabel.setMoveConditions(500, 100, true, true);
        contentpane.add(BirdLabel);

        HorseLabel = new MyLabel("resources/horse.png", "resources/motorcycle.png");
        HorseLabel.setMoveConditions(100, 200, true, false);
        contentpane.add(HorseLabel);

        targetLabel = BirdLabel;
        addKeyListener(this);
        repaint();
    }

    public void keyTyped(KeyEvent e) {

        /*if(e.getKeyCode()== KeyEvent.VK_B)
            BirdLabel.resetIcon();
        if(e.getKeyCode()== KeyEvent.VK_P)
            BirdLabel.switchIcon();
        if(e.getKeyCode()== KeyEvent.VK_H)
            HorseLabel.resetIcon();
        if(e.getKeyCode()== KeyEvent.VK_M)
            HorseLabel.switchIcon();*/
    }

    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        // change picture
        if (e.getKeyCode() == KeyEvent.VK_B) {
            BirdLabel.resetIcon();
            targetLabel = BirdLabel;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            BirdLabel.switchIcon();
            targetLabel = BirdLabel;
        }
        if (e.getKeyCode() == KeyEvent.VK_H) {
            HorseLabel.resetIcon();
            targetLabel = HorseLabel;
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            HorseLabel.switchIcon();
            targetLabel = HorseLabel;
        }
        // move

        
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            targetLabel.moveUp();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            targetLabel.moveDown();
        }
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            targetLabel.moveLeft();
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            targetLabel.moveRight();
    }

    public void keyReleased(KeyEvent e) {
        // System.out.println(e.getKeyCode());

    }

    public static void main(String[] args) {
        new FourCharactersFrame();
    }
}

class MyLabel extends JLabel implements MouseListener {

    private int width = 180, height = 200;      // adjust label size as you want
    private MyImageIcon icon1, icon2;
    private int curX, curY;
    private boolean horizontalMove, verticalMove;

    public MyLabel(String file1, String file2) {

        icon1 = new MyImageIcon(file1).resize(width, height);
        icon2 = new MyImageIcon(file2).resize(width, height);
        setHorizontalAlignment(JLabel.CENTER);

        resetIcon();
        addMouseListener(this);

    }

    public void resetIcon() {
        setIcon(icon1);
    }

    public void switchIcon() {
        setIcon(icon2);
    }

    public void setMoveConditions(int x, int y, boolean h, boolean v) {
        curX = x;
        curY = y;
        setBounds(curX, curY, width, height);
        horizontalMove = h;
        verticalMove = v;

    }

    // add your own code
    public void moveUp() {

        if (verticalMove) {
            curY -= 5;
            Container p = getParent();
            if (curY < 0) {
                curY = 0;
            }

        }
        setBounds(curX, curY, width, height);

    }

    public void moveDown() {

        if (verticalMove) {
            curY += 5;
            Container p = getParent();
            if (curY + height > p.getHeight()) {
                curY = p.getHeight() - height;
            }

        }
         setBounds(curX, curY, width, height);
    }

    public void moveLeft() {
        if (horizontalMove) {
            curX -= 5;
            Container p = getParent();
            if (curX < 0) {
                curX = p.getWidth() - curX - width ;
            }

        }
         setBounds(curX, curY, width, height);
        //setMoveConditions(curX, curY, horizontalMove, verticalMove);
    }

    public void moveRight() {
        
         if (horizontalMove) {
            curX += 5;
            Container p = getParent();
            if (curX  + width > p.getWidth()) {
                curX = curX +  width - p.getWidth();
            }

        }
         setBounds(curX, curY, width, height);
        
        
    }

   
    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON3) {
            setIcon(null);
        }

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}

// auxiliary class to resize image
class MyImageIcon extends ImageIcon {

    public MyImageIcon(String fname) {
        super(fname);
    }

    public MyImageIcon(Image image) {
        super(image);
    }

    public MyImageIcon resize(int width, int height) {
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
};
