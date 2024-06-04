package PvZ.Bullet;

import java.awt.Graphics2D;
import java.awt.Image;
import PvZ.Zombies.Zombie;

public class Bullet {
    private int x, y;
    private int damage,speed = 8;
    private Image image;
    private boolean Collide=false;
    public Bullet(int x, int y,int damage, Image image) {
        this.x = x;
        this.y = y;
        this.damage=damage;
        this.image = image;
    }
    public int getX() {return x;}
    public int getY() {return y;}
    public int getDamage() {return damage;}
    public boolean isCollide() {return Collide;}
    public void setCollide(boolean collide) {Collide = collide;}
    public void update() {x += speed;}
    public void CheckCollide(Zombie zombie){
        if(Math.abs(zombie.getX() - this.getX()) <= 20 && zombie.getY()==this.getY()){
            setCollide(true);
            zombie.setHP(zombie.getHP()-this.getDamage());}
    }
    public void render(Graphics2D g2) {
        g2.drawImage(image, x, y+12,30,30 ,null);}
}
