package PvZ.PvZ_Manager.Plant;

import java.awt.*;
import java.util.List;
import PvZ.PvZ_Manager.Zombie.Zombie;
import PvZ.Sun.SunDrop;

public abstract class Plants {
    private int X,Y,row;
    private int HP=100,damage,price;
    private boolean frozzen;
    private boolean Dead;
    private Rectangle bounds;
    private double timeCDaction;
    private long lastActionkTime;
    private boolean isAction;
    
    public Plants(int X,int row,boolean frozzen){
        this.frozzen=frozzen;
        this.X=X;
        this.row=row;
        bounds=new Rectangle(X,Y,55,55);
    }

    public int getX() {return X;}
    public void setX(int x) {X = x;}
    public int getY() {return (row-1)*109+115;}
    public int getRow() {return row;}
    public Rectangle getBounds(){return bounds;}
    public int getHP() {return HP;}
    public void setHP(int hp) {HP =hp;}
    public boolean isDead() {return Dead;}
    public void setDead(boolean dead) {Dead = dead;}
    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;} 
    public int getDamage() {return damage;}
    public void setDamage(int damage) {this.damage = damage;}
    public boolean isFrozzen() {return frozzen;}
    public void setFrozzen(boolean frozzen) {this.frozzen = frozzen;}
    public boolean isAction() {return isAction;}
    public void setisAction(boolean checkAction) {this.isAction=checkAction;}
    public double getTimeCDaction() {return timeCDaction;}
    public void setTimeCDaction(double timeCDaction) {this.timeCDaction = timeCDaction;}
    public long getLastActionkTime() {return lastActionkTime;}
    public void setLastActionkTime(long lastActionkTime) {this.lastActionkTime = lastActionkTime;}
    public abstract void importImage();
    public abstract void Action(Zombie zombie,SunDrop sunDrop);
    public abstract void renderPlantsAction(Graphics2D g2,List<Zombie> zombieList);
}
