package PlantsPackage;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import Manager.PlantsManager;

public class Shovel {
    private Image shovelImage;
    private boolean isChoose=false;
    private Rectangle bounds;
    private OptionPlants op;
    private PlantsManager plantsManager;
    public Shovel(PlantsManager plantsManager,OptionPlants op){
        this.plantsManager=plantsManager;
        this.op=op;
        importImage();
        bounds=new Rectangle(1200,600,80,100);
    }
    public Rectangle getBounds() {return bounds;}
    public boolean isChoose() {return isChoose;}
    public void setChoose(boolean isChoose) {this.isChoose = isChoose;}
    public void importImage(){
        try {shovelImage=ImageIO.read(getClass().getResourceAsStream("/Plants/Shovel/Shovel.png"));
        } catch (IOException e) {e.printStackTrace();}
    }
    public void render(Graphics2D g2){
        g2.drawImage(shovelImage, bounds.x,bounds.y,bounds.width,bounds.height, null);
    }
    public void chooseShovel(int mouseX,int mouseY){
        if(this.getBounds().contains(mouseX,mouseY)&&this.isChoose()==false){
            setChoose(true);
        }
        else if(this.getBounds().contains(mouseX,mouseY)&&this.isChoose()==true){
            setChoose(false);
        }
    }
    public void RemovePlant(int mouseX,int mouseY){
        int row = -1, col = -1;
        if (115 < mouseY && mouseY <= 224) row = 0;
        else if (224 < mouseY && mouseY <= 333) row = 1;
        else if (333 < mouseY && mouseY <= 442) row = 2;
        else if (442 < mouseY && mouseY <= 551) row = 3;
        else if (551 < mouseY && mouseY <= 660) row = 4;
        if (345 <= mouseX && mouseX <= 435) col = 0;
        else if (435 < mouseX && mouseX <= 525) col = 1;
        else if (525 < mouseX && mouseX <= 615) col = 2;
        else if (615 < mouseX && mouseX <= 705) col = 3;
        else if (705 < mouseX && mouseX <= 795) col = 4;
        else if (795 < mouseX && mouseX <= 885) col = 5;
        else if (885 < mouseX && mouseX <= 975) col = 6;
        else if (965 < mouseX && mouseX <= 1055) col = 7;
        else if (1055 < mouseX && mouseX <= 1145) col = 8;
        if(row!=-1 && col!=-1 && this.isChoose==true && op.isOccupied(row, col)==true){
            for(Plants plants:plantsManager.getPlantsList()){
                if(plants.getX()==345 + 90 * col&&plants.getRow()==row+1){
                    plants.setHP(0);
                    setChoose(false);

                }
            }
        }
    }
}
