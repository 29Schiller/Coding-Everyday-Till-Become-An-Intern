package Audio;

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
            clips = null;
        }
    }
    
   
    public void play_Theme_Start(){
        inputAudio(7);
        clips.start();
        clips.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void ZombieEat(){
        inputAudio(1);
        clips.start();
    }

    public void ZombieStart(){
        inputAudio(8);
        clips.start();
    }

    public void Win(){
        inputAudio(4);
        clips.start();
    }

    public void Lose(){
        inputAudio(5);
        clips.start();
    }

    public void sunCollected(){
        inputAudio(3);
        clips.start();
    }
    public void PlantDeath(){
        inputAudio(2);
        clips.start();
    }
    public void ChoosePlant(){
        inputAudio(0);
        clips.start();
    }
    public void CrazyDaveScream(){
        inputAudio(6);
        clips.start();
    }
}
