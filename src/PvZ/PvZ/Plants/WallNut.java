package PvZ.Plants;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import PvZ.Zombies.Zombie;
import Sun.SunDrop;
public class WallNut extends Plants{
    private int timeIndex=0;
    private int frameDelay = 3;
    private int frameCounter = 0;
    private Image[] WallNutImage=new Image[12];
    public WallNut(int X, int row, boolean frozzen) {
        super(X, row, frozzen);
        setHP(250);
        setDamage(0);
        setPrice(100);
        setFrozzen(false);
        importImage();
    }

    @Override
    public void importImage() {
        for(int i=0; i<WallNutImage.length;i++){
            try {WallNutImage[i]=ImageIO.read(getClass().getResourceAsStream("/Plants/WallNut/"+(i+1)+".png"));
            } catch (IOException e) {e.printStackTrace();}}
    }

    @Override
    public void Action(Zombie zombie,SunDrop sunDrop) {
    }

    @Override
    public void renderPlantsAction(Graphics2D g2,List<Zombie> zombieList) {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
        g2.drawImage(WallNutImage[timeIndex], getX() +15, getY(), 70, 80, null);
        timeIndex = (timeIndex + 1) % WallNutImage.length;
        }
        else{
            g2.drawImage(WallNutImage[timeIndex], getX() +15, getY(), 70, 80, null);
        }
    }


}