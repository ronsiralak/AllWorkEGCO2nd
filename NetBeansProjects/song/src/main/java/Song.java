
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ron
 */
public class Song {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filepath = "music/Menu.wav";
        musicStuff musicObject = new musicStuff(filepath);
        musicObject.playLoop();
        JOptionPane.showMessageDialog(null, "lu7urg");
    }

}
