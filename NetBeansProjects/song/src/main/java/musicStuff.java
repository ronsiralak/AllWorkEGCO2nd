
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
public class musicStuff {

    private java.applet.AudioClip audio;

    public musicStuff(String filename) {
        try {
            java.io.File file = new java.io.File(filename);
            audio = java.applet.Applet.newAudioClip(file.toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playOnce() {
        audio.play();

    }

    public void playLoop() {
        audio.loop();
    }

    public void stop() {
        audio.stop();
    }

}
