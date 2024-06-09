package game.audio;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.net.URL;

public class AudioGame {
    private  Clip clips ;
    static URL soundURL[] = new URL[9];
    public AudioGame() {
        try {
            soundURL[0] = AudioGame.class.getResource("/Audio/tapPlantBar.wav");
            soundURL[1] = AudioGame.class.getResource("/Audio/ZombieEat.wav");
            soundURL[2] = AudioGame.class.getResource("/Audio/PlantDeath.wav");
            soundURL[3] = AudioGame.class.getResource("/Audio/sunCollected.wav");
            soundURL[4] = AudioGame.class.getResource("/Audio/Win.wav");
            soundURL[5] = AudioGame.class.getResource("/Audio/Lose.wav");
            soundURL[6] = AudioGame.class.getResource("/Audio/CrazyDaveScream.wav");
            soundURL[7] = AudioGame.class.getResource("src\\res\\Audio\\main_theme.ogg");
            soundURL[8] = AudioGame.class.getResource("/Audio/ZombieStart.ogg");
        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot open audio!"); 
        }
    }
   
    public void inputAudio(int i){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clips = AudioSystem.getClip();
            clips.open(audioInputStream);
        } catch (Exception e) {
            System.out.println("Error loading audio file: " + e.getMessage());
            clips = null; // Set clips to null if an exception occurs
        }
    }
    
    public void play(){
        if (clips != null) {
            clips.start();
        } else {
            System.out.println("Error: Clips object is null");
        }
    }

    public void loop(){
        clips.loop(clips.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clips.stop();
    }
    /*
    public static void sunCollected(){
        if (clips[3] != null) {
        clips[3].setFramePosition(0);
        clips[3].start();
    } else {
        System.err.println("Đoạn âm thanh cho sunCollected không được tải đúng cách.");
        }
    }

    public static void main_theme(){
        inputAudio(7);
        clips[7].start();
    }
         */
}
