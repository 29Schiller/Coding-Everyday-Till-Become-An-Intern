package PvZ.Zombies;
import java.util.ArrayList;
import java.util.Random;

import PvZ.Plants.Plants;
public class ZombieManager {
    Plants plants;
    Random random=new Random();
    private ArrayList<Zombie> zombieList= new ArrayList<>();
    private ArrayList<Zombie> zombieRemove=new ArrayList<>();
    private static int CountZombie=0;
    public ZombieManager(){}
    public void SpawnZombie(){
        int Startrow=random.nextInt(5)+1;
        int type=random.nextInt(2);
        if (type==0){
            zombieList.add(new MaleZombie(1300,Startrow));
            setCountZombie(getCountZombie()+1);
        }
        else if(type==1){
            zombieList.add(new FemaleZombie(1300, Startrow));
            setCountZombie(getCountZombie()+1);
        }
    }
    public void ZombieAction(ArrayList<Plants> plantsList) {
        for (Zombie zombie : zombieList) {
            Plants closestPlant = null;
            int minDistance = Integer.MAX_VALUE;
            for (Plants plant : plantsList) {
                if (zombie.getRow() == plant.getRow()) {
                    int distance = Math.abs(zombie.getX() - plant.getX());
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestPlant = plant;
                    }
                }
            }
            if (closestPlant != null) {
                zombie.CollidePlants(closestPlant);
                if (zombie.isCollide()) {
                    zombie.setCollide(true);
                    zombie.attack(closestPlant);
                    zombie.winplants(closestPlant);
                } else {
                    zombie.move();
                }
            } else {
                zombie.move();
            }
        }
    }
    public void ZombieDead(){
        zombieRemove.clear();
        for(Zombie zombie: zombieList){
            if(zombie.getHP()<=0){
                zombieRemove.add(zombie);
            }
        }
        zombieList.removeAll(zombieRemove);
    }
    public ArrayList<Zombie> ZombieList() {
        return zombieList;
    }
    public static int getCountZombie() {
        return CountZombie;
    }
    public static void setCountZombie(int countZombie) {
        CountZombie = countZombie;
    }
}
