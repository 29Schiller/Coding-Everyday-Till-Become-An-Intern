package Manager;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import PlantsPackage.Plants;
import ZombiesPackage.FemaleZombie;
import ZombiesPackage.MaleZombie;
import ZombiesPackage.Zombie;
import gui.Playing;

public class ZombieManager {
    Plants plants;
    static Random random = new Random();
    private static int level = random.nextInt(6) + 1;
    private List<Zombie> zombieList = new CopyOnWriteArrayList<>();
    private static int CountZombie = 0;
    private static int Points=0;
    public ZombieManager() {}

    public void SpawnZombie() {
        if (Playing.isGameEnded()==true) {
            return;
        }
        int Startrow = random.nextInt(5) + 1;
        int type = random.nextInt(2);
        if (type == 0) {
            zombieList.add(new MaleZombie(1300, Startrow));
            setCountZombie(getCountZombie() + 1);
            Points += 10;
        } else if (type == 1) {
            zombieList.add(new FemaleZombie(1300, Startrow));
            setCountZombie(getCountZombie() + 1);
            Points += 20;
        }
    }

    public void ZombieAction(List<Plants> plantsList) {
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

    public void ZombieDead() {
        for (Zombie zombie : zombieList) {
            if (zombie.getHP() <= 0 && zombie.isDeadAnimationFinished()) {
                zombieList.remove(zombie);
            }
        }
    }

    public List<Zombie> ZombieList() {
        return zombieList;
    }

    public static int getCountZombie() {
        return CountZombie;
    }

    public static void setCountZombie(int countZombie) {
        CountZombie = countZombie;
    }

    public static int getLevel() {
        return level;
    }
    public void render(Graphics2D g2){
        synchronized (this.ZombieList()) {
            for (Zombie zombie : new CopyOnWriteArrayList<>(this.ZombieList())) {
                zombie.renderZombiesAction(g2);
            }
        }
    }
    public static int getPoints() {
        return Points;
    }
    public static void setPoints(int points) {
        Points = points;
    }
    public static void resetStaticVariables() {
        CountZombie = 0;
        Points = 0;
        level = random.nextInt(6) + 1;
    }   
}