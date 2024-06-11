package Gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.net.URL;

public class AudioManager {
    private  Clip clips ;
    static URL soundURL[] = new URL[9];

    public AudioManager() {
        try {
            soundURL[0] = AudioManager.class.getResource("/Resource/Audio/tapPlantBar.wav");
            soundURL[1] = AudioManager.class.getResource("/Resource/Audio/ZombieEat.wav");
            soundURL[2] = AudioManager.class.getResource("/Resource/Audio/PlantDeath.wav");
            soundURL[3] = AudioManager.class.getResource("/Resource/Audio/sunCollected.wav");
            soundURL[4] = AudioManager.class.getResource("/Resource/Audio/Win.wav");
            soundURL[5] = AudioManager.class.getResource("/Resource/Audio/Lose.wav");
            soundURL[6] = AudioManager.class.getResource("/Resource/Audio/CrazyDaveScream.wav");
            soundURL[7] = AudioManager.class.getResource("/Resource/Audio/main_theme.wav");
            soundURL[8] = AudioManager.class.getResource("/Resource/Audio/ZombieStart.wav");
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
        inputAudio(7);
        clips.start();
        clips.loop(clips.LOOP_CONTINUOUSLY);
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

    public void tapPlantBar(){
        inputAudio(0);
        clips.start();
    }

}
