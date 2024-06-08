package PvZ.Lawn;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    public synchronized void Action(List<Zombie> list){
        for (Lawn lawn : lawnsList) {
            for (Zombie zombie : list) {
                lawn.CheckCollide(zombie);
            }
            if (lawn.isAction()==true) {
                lawn.move();
                for (Zombie zombie : list) {
                    lawn.CheckCollide(zombie);
                }
            }
        }
    }
    public void LawnDead(){
        synchronized (lawnsList) {
            Iterator<Lawn> iterator = lawnsList.iterator();
            while (iterator.hasNext()) {
                Lawn lawn = iterator.next();
                if (lawn.getX()>=1300) {
                    iterator.remove();
                }
            }
        }
    }
    public synchronized ArrayList<Lawn> getLawnsList() {
        return lawnsList;
    }
    public synchronized void render(Graphics2D g2){
        for(Lawn lawn:lawnsList){
            lawn.renderLawn(g2);
        }
    }
}
