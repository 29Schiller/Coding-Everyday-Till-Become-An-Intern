package ZombiesPackage;

import javax.imageio.ImageIO;

import Audio.AudioGame;
import PlantsPackage.Plants;

import java.io.IOException;
import java.awt.*;
public class MaleZombie extends Zombie {
    private Image[] normalZombieMoveImage = new Image[10];
    private Image[] normalZombieAttackImage = new Image[8];
    private Image[] MaleZombieDead = new Image[12];
    private int moveIndex = 0;
    private int attackIndex = 0;
    private int deadIndex = 0;
    private int frameDelay = 3;
    private int frameCounter = 0;
    private boolean isDeadAnimationFinished = false;
    private AudioGame sAudioGame = new AudioGame();
    public MaleZombie(int X, int row) {
        super(X, row);
        setHP(100);
        setSpeed(10);
        setDamge(20);
        setAttackWaitingTime(1000);
        importImage();
        setCollide(false);
    }

    public void importImage() {
        for (int i = 0; i < normalZombieMoveImage.length; i++) {
            try {
                normalZombieMoveImage[i] = ImageIO.read(getClass().getResourceAsStream("/Zombie/MaleZombie/ZombieMove/Walk (" + (i + 1) + ").png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < normalZombieAttackImage.length; i++) {
            try {
                normalZombieAttackImage[i] = ImageIO.read(getClass().getResourceAsStream("/Zombie/MaleZombie/ZombieAttack/Attack (" + (i + 1) + ").png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < MaleZombieDead.length; i++) {
            try {
                MaleZombieDead[i] = ImageIO.read(getClass().getResourceAsStream("/Zombie/MaleZombie/ZombieDead/Dead (" + (i + 1) + ").png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void move() {
        if (!isCollide() && getX() >= 200) {
            setX(X -= speed);
        }
    }

    @Override
    public void CollidePlants(Plants plants) {
        if (this.getRow() == plants.getRow() && this.getX() - plants.getX() >= 0 && this.getX() - plants.getX() <= 10) {
            this.setCollide(true);
        }
    }

    @Override
    public void attack(Plants plants) {
        long currentTime = System.currentTimeMillis();
        if (isCollide() && (currentTime - getLastAttackTime()) >= getAttackWaitingTime()) {
            sAudioGame.ZombieEat();
            plants.setHP(plants.getHP() - this.getDamage());
            setLastAttackTime(currentTime);
        }
    }

    @Override
    public void winplants(Plants plants) {
        if (plants.getHP() <= 0) {
            setCollide(false);
        }
    }

    @Override
    public void renderZombiesAction(Graphics2D g2) {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            if(this.getHP()>0){
                if (isCollide()==false) {
                    g2.drawImage(normalZombieMoveImage[moveIndex], getX(), getY()-(150/2), 110, 150, null);
                    moveIndex = (moveIndex + 1) % normalZombieMoveImage.length;
                } else {
                    g2.drawImage(normalZombieAttackImage[attackIndex], getX(), getY()-(150/2), 110, 150, null);
                    attackIndex = (attackIndex + 1) % normalZombieAttackImage.length; }
            }
            else {
                if (deadIndex < MaleZombieDead.length) {
                    g2.drawImage(MaleZombieDead[deadIndex], getX(), getY() - (150 / 2), 110, 150, null);
                    deadIndex++;
                } else {
                    isDeadAnimationFinished = true;
                }
            }
        }
        else{
            if(this.getHP()>0){
                if (isCollide()==false) {
                    g2.drawImage(normalZombieMoveImage[moveIndex], getX(), getY()-(150/2), 110, 150, null);
                } else {
                    g2.drawImage(normalZombieAttackImage[attackIndex], getX(), getY()-(150/2), 110, 150, null);}
            }else {
                if (deadIndex < MaleZombieDead.length) {
                    g2.drawImage(MaleZombieDead[deadIndex], getX(), getY() - (150 / 2), 110, 150, null);}    
        }
    }
}

    public boolean isDeadAnimationFinished() {
        return isDeadAnimationFinished;
    }
}