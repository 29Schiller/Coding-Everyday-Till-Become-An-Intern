package PvZ.PvZ_Manager.Zombie;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import PvZ.PvZ_Manager.Plant.Plants;

public abstract class Zombie {
    private int HP ,damage;
    protected int speed;
    private int width = 55, height = 110;
    private double attackWaitingTime;
    private long lastAttackTime;
    protected int X,Y;
	private int row;
    private boolean isCollide;
    private Rectangle bounds;
    public Zombie(int X,int row) {
        this.X=X;
        this.row=row;
        this.lastAttackTime = System.currentTimeMillis();
        bounds = new Rectangle(X, Y, 55, 110);
    }
    
    public Rectangle getBounds() {return bounds;}
    public int getHP() {return HP;}
    public void setHP(int health) {this.HP = health;}
    public int getDamage() {return damage;}
    public void setDamge(int damage){this.damage=damage;}
    public double getAttackWaitingTime() {return attackWaitingTime;}
    public int getSpeed() {return speed;}
    public void setAttackWaitingTime(double attackWaitingTime) {this.attackWaitingTime = attackWaitingTime;}
    public void setSpeed(int speed) {this.speed = speed;}
    public int getY() {return (row-1)*109+115;}
    public int getX() {return X;}
    public void setX(int x) {
        X = x;
        bounds.setLocation(X, Y);
    }
    public int getRow() {return row;}
    public boolean isCollide() {return isCollide;}
    public void setCollide(boolean isCollide) {this.isCollide = isCollide;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public long getLastAttackTime() {return lastAttackTime;}
    public void setLastAttackTime(long lastAttackTime) {this.lastAttackTime = lastAttackTime;}
    public abstract boolean isDeadAnimationFinished();
    public abstract void move();
    public abstract void CollidePlants(Plants plants);
    public abstract void attack(Plants plants);
    public abstract void winplants(Plants plants);
    public abstract void renderZombiesAction(Graphics2D g2); 
}
