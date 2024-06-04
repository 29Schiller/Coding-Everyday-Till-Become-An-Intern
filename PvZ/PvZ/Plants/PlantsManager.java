package PvZ.Plants;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
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

    public void SpawnPlants(String Type,int X,int row,SunDrop sunDrop) {
        if (Type.equals("Pea")) {
            Plants pea=new Pea(X, row, false);
            if(sunDrop.getSunscore()>=pea.getPrice()){
                plantsList.add(pea);
                int col = (X - 397) / 90;
                optionPlants.setOccupied(row - 1, col, true);
                sunDrop.setSunscore(sunDrop.getSunscore()-pea.getPrice());
            }
            else {
                plantsUnpaid.add(pea);
                plantsUnpaid.clear();
            }
        }
        if (Type.equals("SunFlower")) {
            Plants sunFlower=new SunFlower(X, row, false);
            if(sunDrop.getSunscore()>=sunFlower.getPrice()){
                plantsList.add(sunFlower);
                int col = (X - 397) / 90;
                optionPlants.setOccupied(row - 1, col, true);
                sunDrop.setSunscore(sunDrop.getSunscore()-sunFlower.getPrice());
            }
            else {
                plantsUnpaid.add(sunFlower);
                plantsUnpaid.clear();
            }
        }
        if (Type.equals("WallNut")) {
            Plants wallNut=new WallNut(X, row, false);
            if(sunDrop.getSunscore()>=wallNut.getPrice()){
                plantsList.add(wallNut);
                int col = (X - 397) / 90;
                optionPlants.setOccupied(row - 1, col, true);
                sunDrop.setSunscore(sunDrop.getSunscore()-wallNut.getPrice());
            }
            else {
                plantsUnpaid.add(wallNut);
                plantsUnpaid.clear();
            }
        }
    }

    public void DeadPlant() {
        Iterator<Plants> iterator = plantsList.iterator();
        while (iterator.hasNext()) {
            Plants plant = iterator.next();
            if (plant.getHP() <= 0) {
                int row = plant.getRow() - 1;
                int col = (plant.getX() - 397) / 90;;
                iterator.remove();
                optionPlants.setOccupied(row, col, false);
            }
        }
    }

    public void PlantsAction(ArrayList<Zombie> zombieList, SunDrop sunDrop) {
        ArrayList<Zombie> zombiesCopy = new ArrayList<>(zombieList);
        for (Plants plants : plantsList) {
            for (Zombie zombie : zombiesCopy) {
                if (zombie.getRow() == plants.getRow()) {
                    if (plants instanceof SunFlower) {
                        ((SunFlower) plants).Action(zombie, sunDrop);
                    } else {
                        plants.Action(zombie, sunDrop);
                    }
                }
            }
        }
    }

    public void render(Graphics2D g2,ArrayList<Zombie> zombieList,SunDrop sunDrop) {
        optionPlants.renderImage(g2);
        for (Plants plants : plantsList) {
            plants.renderPlantsAction(g2,zombieList); 
        }
    }
            
    public ArrayList<Plants> getPlantsList() {
        return plantsList;
    }
}