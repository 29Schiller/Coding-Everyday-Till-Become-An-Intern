package PvZ.Zombies;
import javax.imageio.ImageIO;
import PvZ.Plants.Plants;
import java.io.File;
import java.io.IOException;
import java.awt.*;
public class MaleZombie extends Zombie {
    private Image[] normalZombieMoveImage = new Image[10];
    private Image[] normalZombieAttackImage = new Image[8];
    private boolean isRepeat = false;
    private int moveIndex = 0;
    private int attackIndex = 0; 
    public MaleZombie(int X, int row) {
        super(X, row);
        setHP(100);
        setSpeed(4);
        setDamge(25);
        setAttackWaitingTime(1000);
        importImage(); 
        setCollide(false);
    }

    public void importImage() {
        for (int i = 0; i < normalZombieMoveImage.length; i++) {
            try {normalZombieMoveImage[i] = ImageIO.read(new File("D:/Code/GitHub/OOP-Game-OcCho/res/Zombie/MaleZombie/ZombieMove/Walk (" + (i + 1) + ").png"));
                } catch (IOException e) {e.printStackTrace();}}
        for (int i = 0; i < normalZombieAttackImage.length; i++) {
            try {normalZombieAttackImage[i] = ImageIO.read(new File("D:/Code/GitHub/OOP-Game-OcCho/res/Zombie/MaleZombie/ZombieAttack/Attack (" + (i + 1) + ").png"));
                } catch (IOException e) {e.printStackTrace();}}}

    @Override
    public void move() {
        if (this.isCollide()!=true && getX()>=250) {
            setX(X -= speed);}}
    @Override
    public void CollidePlants(Plants plants) {
        if (this.getRow() == plants.getRow() && this.getX() - plants.getX()>=0&&this.getX() - plants.getX() <= 10) {
            this.setCollide(true);
        }
    }
    @Override
    public void attack(Plants plants) {
        long currentTime = System.currentTimeMillis();
        if (isCollide()==true && (currentTime - getLastAttackTime()) >= getAttackWaitingTime()) {
            plants.setHP(plants.getHP() - this.getDamage());
            setLastAttackTime(currentTime);
        }
    }

    @Override
    public void winplants(Plants plants) {
        if(plants.getHP()<=0){
        setCollide(false);}}

    @Override
    public void renderZombiesAction(Graphics2D g2) {
        if (!isRepeat) {
            if (isCollide()==false) {
                g2.drawImage(normalZombieMoveImage[moveIndex], getX(), getY()-(150/2), 90, 150, null);
                moveIndex = (moveIndex + 1) % normalZombieMoveImage.length;
            } else {
                g2.drawImage(normalZombieAttackImage[attackIndex], getX(), getY()-(150/2), 90, 150, null);
                attackIndex = (attackIndex + 1) % normalZombieAttackImage.length; }}}
}