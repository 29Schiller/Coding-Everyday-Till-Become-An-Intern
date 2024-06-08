package PvZ.Plants;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import PvZ.Zombies.Zombie;
import Sun.SunDrop;

public class PlantsManager {
    private OptionPlants optionPlants;
    private ArrayList<Plants> plantsList = new ArrayList<>();
    private ArrayList<Plants> plantsUnpaid = new ArrayList<>();

    public PlantsManager() {
        this.optionPlants = new OptionPlants();
        this.optionPlants.importImage();
    }

    public void SpawnPlants(String type, int x, int row, SunDrop sunDrop) {
        Plants plant = null;
        if (type.equals("Pea")) {
            plant = new Pea(x, row, false);
        } else if (type.equals("SunFlower")) {
            plant = new SunFlower(x, row, false);
        } else if (type.equals("WallNut")) {
            plant = new WallNut(x, row, false);
        }

        if (plant != null) {
            if (sunDrop.getSunscore() >= plant.getPrice()) {
                synchronized (plantsList) {
                    plantsList.add(plant);
                }
                int col = (x - 345) / 90;
                optionPlants.setOccupied(row - 1, col, true);
                sunDrop.setSunscore(sunDrop.getSunscore() - plant.getPrice());
            } else {
                plantsUnpaid.add(plant);
                plantsUnpaid.clear();
            }
        }
    }

    public void DeadPlant() {
        synchronized (plantsList) {
            Iterator<Plants> iterator = plantsList.iterator();
            while (iterator.hasNext()) {
                Plants plant = iterator.next();
                if (plant.getHP() <= 0) {
                    int row = plant.getRow() - 1;
                    int col = (plant.getX() - 345) / 90;
                    iterator.remove();
                    optionPlants.setOccupied(row, col, false);
                }
            }
        }
    }

    public void PlantsAction(ArrayList<Zombie> zombieList, SunDrop sunDrop) {
        ArrayList<Plants> plantsCopy;
        synchronized (plantsList) {
            plantsCopy = new ArrayList<>(plantsList);
        }
        for (Plants plant : plantsCopy) {
            for (Zombie zombie : zombieList) {
                if (zombie.getRow() == plant.getRow()) {
                    if (plant instanceof SunFlower) {
                        ((SunFlower) plant).Action(zombie, sunDrop);
                    } else {
                        plant.Action(zombie, sunDrop);
                    }
                }
            }
        }
    }

    public void render(Graphics2D g2, List<Zombie> list, SunDrop sunDrop) {
        optionPlants.renderImage(g2);
        ArrayList<Plants> plantsCopy;
        synchronized (plantsList) {
            plantsCopy = new ArrayList<>(plantsList);
        }
        for (Plants plant : plantsCopy) {
            plant.renderPlantsAction(g2, list);
        }
    }

    public ArrayList<Plants> getPlantsList() {
        return plantsList;
    }
}