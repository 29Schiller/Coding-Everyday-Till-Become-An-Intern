package PvZ.Zombies;
import javax.imageio.ImageIO;
import PvZ.Plants.Plants;
import java.io.IOException;
import java.awt.*;
public class FemaleZombie extends Zombie {
    private Image[] FemaleZombieMoveImage = new Image[10];
    private Image[] FemaleZombieAttackImage = new Image[8];
    private Image[] FemaleZombieDeadImage = new Image[12];
    private int moveIndex = 0;
    private int attackIndex = 0; 
    private int deadIndex = 0;
    private int frameDelay = 4;
    private int frameCounter = 0;
    private boolean isDeadAnimationFinished = false;
    public FemaleZombie(int X, int row) {
        super(X, row);
        setHP(160);
        setSpeed(6);
        setDamge(25);
        setAttackWaitingTime(1000);
        importImage(); 
        setCollide(false);
    }

    public void importImage() {
        for (int i = 0; i < FemaleZombieMoveImage.length; i++) {
            try {FemaleZombieMoveImage[i] = ImageIO.read(getClass().getResourceAsStream("/Zombie/FemaleZombie/Walk/Walk ("+(i+1)+").png"));
                } catch (IOException e) {e.printStackTrace();}}
        for (int i = 0; i < FemaleZombieAttackImage.length; i++) {
            try {FemaleZombieAttackImage[i] = ImageIO.read(getClass().getResourceAsStream("/Zombie/FemaleZombie/Attack/Attack ("+(i+1)+").png"));
                } catch (IOException e) {e.printStackTrace();}}
        for (int i = 0; i < FemaleZombieDeadImage.length; i++) {
            try {FemaleZombieDeadImage[i] = ImageIO.read(getClass().getResourceAsStream("/Zombie/FemaleZombie/Dead/Dead ("+(i+1)+").png"));
                } catch (IOException e) {e.printStackTrace();}}
    }

    @Override
    public void move() {
        if (this.isCollide()!=true && getX()>=250) {
            setX(X -= speed);}}

    @Override
    public void CollidePlants(Plants plants) {
        if (this.getRow() == plants.getRow()&& this.getX() - plants.getX() >=0 && this.getX() - plants.getX() <= 20) {
            this.setCollide(true);}}

    @Override
    public void attack(Plants plants) {
        long currentTime = System.currentTimeMillis();
        if (isCollide()==true && (currentTime - getLastAttackTime()) >= getAttackWaitingTime()) {
            plants.setHP(plants.getHP() - this.getDamage());
            setLastAttackTime(currentTime);}}

    @Override
    public void winplants(Plants plants) {
        if(plants.getHP()<=0){
        setCollide(false);}}

    @Override
    public void renderZombiesAction(Graphics2D g2) {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            if(this.getHP()>0){
                if (isCollide()==false) {
                    g2.drawImage(FemaleZombieMoveImage[moveIndex], getX(), getY()-(150/2), 110, 150, null);
                    moveIndex = (moveIndex + 1) % FemaleZombieMoveImage.length;
                } else {
                    g2.drawImage(FemaleZombieAttackImage[attackIndex], getX(), getY()-(150/2), 110, 150, null);
                    attackIndex = (attackIndex + 1) % FemaleZombieAttackImage.length; }
            }
            else {
                if (deadIndex < FemaleZombieDeadImage.length) {
                    g2.drawImage(FemaleZombieDeadImage[deadIndex], getX(), getY() - (150 / 2), 110, 150, null);
                    deadIndex++;
                } else {
                    isDeadAnimationFinished = true;
                }
            }
        }
        else{
            if(this.getHP()>0){
                if (isCollide()==false) {
                    g2.drawImage(FemaleZombieMoveImage[moveIndex], getX(), getY()-(150/2), 110, 150, null);
                } else {
                    g2.drawImage(FemaleZombieAttackImage[attackIndex], getX(), getY()-(150/2), 110, 150, null);}
            }else {
                if (deadIndex < FemaleZombieDeadImage.length) {
                    g2.drawImage(FemaleZombieDeadImage[deadIndex], getX(), getY() - (150 / 2), 110, 150, null);}    
        }
    }
}
    public boolean isDeadAnimationFinished() {
        return isDeadAnimationFinished;
    }
}