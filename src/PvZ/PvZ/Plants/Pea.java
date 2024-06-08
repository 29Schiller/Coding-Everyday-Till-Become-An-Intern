package PvZ.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import PvZ.Zombies.Zombie;
import Sun.SunDrop;
import PvZ.Bullet.*;
public class Pea extends Plants {
    private Image[] PeaNoActionImage = new Image[12];
    private Image[] PeaActionImage = new Image[18];
    private Image PeaBulletImage;
    private int attackIndex = 0;
    private int noActionIndex = 0;
    private boolean isDrawingFirstSetNoAction = true;
    private int frameDelay = 3;
    private int frameCounter = 0;
    private boolean bulletSpawned = false;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    public Pea(int X, int row, boolean frozzen) {
        super(X, row, frozzen);
        setHP(100);
        setDamage(20);
        setTimeCDaction(1500);
        setPrice(50);
        setFrozzen(false);
        importImage();
    }

    @Override
    public void importImage() {
        for (int i = 0; i < PeaNoActionImage.length; i++) {
            try {PeaNoActionImage[i] = ImageIO.read(new File("D:/Code/GitHub/OOP-Game-OcCho/res/Plants/Pea/PeaNoAction/" + (i + 1) + ".png"));
            } catch (IOException e) {e.printStackTrace();}}
        for (int i = 0; i < PeaActionImage.length; i++) {
            try {PeaActionImage[i] = ImageIO.read(new File("D:/Code/GitHub/OOP-Game-OcCho/res/Plants/Pea/PeaAction/" + (i + 1) + ".png"));
            } catch (IOException e) {e.printStackTrace();}}
        try {PeaBulletImage = ImageIO.read(new File("D:/Code/GitHub/OOP-Game-OcCho/res/Plants/Pea/Bullet/Pea.png"));
        } catch (IOException e) {e.printStackTrace();}
    }

    @Override
    public void Action(Zombie zombie,SunDrop sunDrop) {
        long currentTime = System.currentTimeMillis();
        if (zombie.getX() - this.getX() <= 800 && zombie.getRow() == this.getRow() && (currentTime - getLastActionkTime()) >= getTimeCDaction()) {
            setisAction(true);
            setLastActionkTime(currentTime); 
        }
    }

    @Override
    public void renderPlantsAction(Graphics2D g2,List<Zombie> zombieList) {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            if (!isAction()) {
                if (isDrawingFirstSetNoAction) {
                    g2.drawImage(PeaNoActionImage[noActionIndex], getX()+15, getY(), 70, 80, null);
                    noActionIndex++;
                    if (noActionIndex >= PeaNoActionImage.length) {
                        noActionIndex = 0;
                        isDrawingFirstSetNoAction = false;
                    }
                } else {
                    g2.drawImage(PeaNoActionImage[11 - noActionIndex], getX()+15, getY(), 70, 80, null);
                    noActionIndex++;
                    if (noActionIndex >= PeaNoActionImage.length) {
                        noActionIndex = 0;
                        isDrawingFirstSetNoAction = true;
                    }
                }
            } else {
                g2.drawImage(PeaActionImage[attackIndex], getX()+15 , getY(), 70, 80, null);
                attackIndex++;
                if (attackIndex == 17 && !bulletSpawned) { 
                    bullets.add(new Bullet(this.getX()+50, this.getY(), this.getDamage(), PeaBulletImage));
                    bulletSpawned = true; 
                }
                if (attackIndex >= PeaActionImage.length) {
                    attackIndex = 0;
                    setisAction(false); 
                    bulletSpawned = false; 
                }
            }
        } else {
            if (!isAction()) {
                if (isDrawingFirstSetNoAction) {
                    g2.drawImage(PeaNoActionImage[noActionIndex], getX() +15, getY(), 70, 80, null);
                } else {
                    g2.drawImage(PeaNoActionImage[11 - noActionIndex], getX()+15, getY(), 70, 80, null);
                }
            } else {
                g2.drawImage(PeaActionImage[attackIndex], getX()+15, getY(), 70, 80, null);
            }
        }
        for (Bullet bullet : bullets) {
            bullet.update();
            bullet.render(g2);
            for (Zombie zombie : zombieList) {
                bullet.CheckCollide(zombie);
            }
        }
        bullets.removeIf(Bullet::isCollide);
}
}