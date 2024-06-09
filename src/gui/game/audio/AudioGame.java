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
            soundURL[7] = AudioGame.class.getResource("/Audio/main_theme.wav");
            soundURL[8] = AudioGame.class.getResource("/Audio/ZombieStart.wav");
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
    
   
    public void play_Theme(){
        inputAudio(8);
        clips.start();
        clips.loop(clips.LOOP_CONTINUOUSLY);
    }
}
