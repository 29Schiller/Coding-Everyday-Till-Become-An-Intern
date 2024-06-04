package PvZ.Lawn;

import java.awt.Graphics2D;
import java.util.ArrayList;

import PvZ.Zombies.Zombie;

public class LawnManager {
    private ArrayList<Lawn> lawnsList=new ArrayList<>();
    public LawnManager(){}
    public void SpawnLawn(){
        lawnsList.add(new Lawn(300, 1));
        lawnsList.add(new Lawn(300, 2));
        lawnsList.add(new Lawn(300, 3));
        lawnsList.add(new Lawn(300, 4));
        lawnsList.add(new Lawn(300, 5));
    }
    public void Action(ArrayList<Zombie> zombieList){
        for (Lawn lawn : lawnsList) {
            for (Zombie zombie : zombieList) {
                lawn.CheckCollide(zombie);
            }
            if (lawn.isAction()==true) {
                lawn.move();
                for (Zombie zombie : zombieList) {
                    lawn.CheckCollide(zombie);
                }
            }
        }
    }
    public ArrayList<Lawn> getLawnsList() {
        return lawnsList;
    }
    public void render(Graphics2D g2){
        for(Lawn lawn:lawnsList){
            lawn.renderLawn(g2);
        }
    }
}
