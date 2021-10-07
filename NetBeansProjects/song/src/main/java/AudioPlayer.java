
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class AudioPlayer {

    public static synchronized void playsound(Audioclip sfx) {
        Thread thread;
        thread = new Thread() {
            public void run() {
                try {
                    AudioInputStream stream = sfx.getAudioStream();
                    /*AudioInputStream pcmIn = AudioSystem.getAudioInputStream(
    new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100f, 16, 1, 2, 44100f, true)
             ,stream);*/
                    Clip clip = AudioSystem.getClip();
                    clip.open(stream);
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    
                    JOptionPane.showMessageDialog(null,"Enter to stop");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            
        };thread.start();
      
    }

}
