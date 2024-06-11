package PvZ.PvZ_Manager.Lawn;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import PvZ.PvZ_Manager.Zombie.*;

public class Lawn {
    private int x;
    private int row;
    private boolean isCollide = false;
    private boolean isAction = false;
    private Image LawnImage;

    public Lawn(int x, int row) {
        this.x = x;
        this.row = row;
        importImage();
    }
    public void importImage() {
        try {
            LawnImage = ImageIO.read(getClass().getResourceAsStream("/Resource/Lawn/Lawnmover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return (row - 1) * 109 + 115;
    }
    public int getRow() {
        return row;
    }
    public boolean isCollide() {
        return isCollide;
    }
    public void setCollide(boolean isCollide) {
        this.isCollide = isCollide;
    }
    public boolean isAction() {
        return isAction;
    }
    public void setAction(boolean isAction) {
        this.isAction = isAction;
    }
    public void move() {
        setX(this.getX() + 20);
    }
    public void CheckCollide(Zombie zombie) {
        if (zombie.getX() - this.getX() <= 10 && zombie.getRow() == this.getRow()) {
            setCollide(true);
            zombie.setHP(0);
            setAction(true);
        }
    }
    public void renderLawn(Graphics2D g2) {
        g2.drawImage(LawnImage, getX() - 40, getY(), 90, 100, null);
    }
}
