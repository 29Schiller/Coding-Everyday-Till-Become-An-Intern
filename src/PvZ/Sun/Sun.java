package PvZ.Sun;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sun {
    private int X;
    private int Y;
    private boolean isCollected;
    private Image sunImage;
    private int destruct = 75;
    private Rectangle bounds;
    public Sun(int X, int Y) {
        this.X = X;
        this.Y = Y;
        try {
            this.sunImage = ImageIO.read(getClass().getResourceAsStream("/Resource/Sun/Sun 6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bounds = new Rectangle(X, Y, 75, 75);
    }
    public boolean isCollected() {return isCollected;}
    
    public void acceptCollected(boolean collect) {
        this.isCollected = collect;}

    public int getX() {return X;}

    public int getY(){return Y;}

    public void setY(int Y) {
        this.Y = Y;
        bounds.setLocation(X,Y);}
    public Rectangle getBounds(){
        return bounds;
    }
    public int getDestruct() {return destruct;}

    public void decrementDestruct() {this.destruct--;}

    public void render(Graphics2D g2) {
        g2.drawImage(sunImage, X, Y, 65, 65, null);
    }
}
