package PlantsPackage;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import Manager.SunManager;
import SunPackage.Sun;
import ZombiesPackage.Zombie;
public class SunFlower extends Plants{
    private int timeIndex=0;
    private int frameDelay = 2;
    private int frameCounter = 0;
    private Image[] SunFlowerImage= new Image[30];
    private ArrayList<Sun> sunList= new ArrayList<>();
    Random random=new Random();
    public SunFlower(int X, int row, boolean frozzen) {
        super(X, row, frozzen);
        setDamage(0);
        setFrozzen(false);
        setHP(100);
        setPrice(50);
        setTimeCDaction(10000);
        importImage();
    }

    @Override
    public void importImage() {
        for (int i=0; i<SunFlowerImage.length;i++){
            try {SunFlowerImage[i]= ImageIO.read(getClass().getResourceAsStream("/Plants/SunFlower/"+(i+1)+".png"));
            } catch (IOException e) {e.printStackTrace();}}
    }

    @Override
public void Action(Zombie zombie, SunManager sunDrop) {
    long currentTime = System.currentTimeMillis();
    if ((currentTime - getLastActionkTime()) >= getTimeCDaction()) {
        setisAction(true);
        setLastActionkTime(currentTime);
        spawnSun(sunDrop);
    }
}
public void spawnSun(SunManager sunDrop) {
    int start_X_Spawn = this.getX() + random.nextInt(50) - 50;
    Sun sun = new Sun(start_X_Spawn, getY());
    sunDrop.SunList().add(sun); 
}
@Override
public void renderPlantsAction(Graphics2D g2, List<Zombie> zombieList) {
    frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
        g2.drawImage(SunFlowerImage[timeIndex], getX() +15, getY(), 70, 80, null);
        timeIndex = (timeIndex + 1) % SunFlowerImage.length;
        }
        else{
            g2.drawImage(SunFlowerImage[timeIndex], getX() +15, getY(), 70, 80, null);
        }
    if (isAction()) {
        for (Sun sun : sunList) {
            sun.render(g2);
        }
    }
}
}
