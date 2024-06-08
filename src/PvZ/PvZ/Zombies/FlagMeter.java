package PvZ.Zombies;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FlagMeter {
    private Image FlagMeterFull;
    private Image FlagMeterEmpty;
    private ZombieManager zombieManager;
    private int totalSpawn=ZombieManager.getLevel()*10;
    private int ratio=244/totalSpawn;
    public FlagMeter(ZombieManager zombieManager){
        zombieManager=new ZombieManager();
        importImage();
    }

    public void importImage(){
        try {FlagMeterFull=ImageIO.read(new File( "D:/Code/GitHub/OOP-Game-OcCho/res/Zombie/Flag/FlagMeterFull.png"));
        } catch (IOException e) {e.printStackTrace();}
        try {FlagMeterEmpty=ImageIO.read(new File( "D:/Code/GitHub/OOP-Game-OcCho/res/Zombie/Flag/FlagMeterEmpty.png"));
        } catch (IOException e) {e.printStackTrace();}
    }
    
    public void render(Graphics2D g2){
        g2.drawImage(FlagMeterFull, 1000, 10,250,30,null);
        g2.drawImage(FlagMeterEmpty,1003,10,244-ratio*ZombieManager.getCountZombie(),30 , null);
    }
}