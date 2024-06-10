package YardPackage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import gui.Playing;

public class Yard {
    public BufferedImage background;

    public Yard(Playing gamePanel){
        getBackground();
    }

    public void getBackground(){
        try {this.background=ImageIO.read(getClass().getResourceAsStream("/Yard.jpg"));
        } catch (IOException e) {e.printStackTrace();}
    }
    
    public void render(Graphics2D g2){
        g2.drawImage(background, 0, 0,1300,750,null);
    }
}
