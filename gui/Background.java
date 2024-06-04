import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background {
    public BufferedImage background;

    public Background(GamePanel gamePanel){
        getBackground();
    }

    public void getBackground(){
        try {this.background=ImageIO.read(new File( "D:/Code/OOP/Project/res/Yard.jpg"));
        } catch (IOException e) {e.printStackTrace();}
    }
    
    public void render(Graphics2D g2){
        g2.drawImage(background, 0, 0,1300,750,null);
    }
}
