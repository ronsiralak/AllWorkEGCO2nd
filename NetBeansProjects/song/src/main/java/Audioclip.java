
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
public class Audioclip{
    private File  file;
    public Audioclip(String path){
    file = new File(path);
    if(!file.exists()){
        System.out.println("Files lost");
    }
    
    }
    public AudioInputStream getAudioStream(){
        
     try{
     return AudioSystem.getAudioInputStream(file);
     
     }catch(Exception e){
     e.printStackTrace();
     
     }   
        return null;
    }
    
}