package Gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Gui.AudioManager;

public class MenuState {
    private GameLoop gameLoop;
    private Image menuGame;
    private Image pvz_logo;
    private Image playButton;
    private Rectangle bounds;
    public AudioManager sound = new AudioManager();

    public MenuState(GameLoop gameLoop){
        this.gameLoop=gameLoop;
        bounds=new Rectangle(400,500,500,100);
        importImage();
        sound.play_Theme();
    }
    public void importImage(){
        try {menuGame=ImageIO.read(getClass().getResourceAsStream("/Resource/MenuGame/wallpaper.jpg"));
        } catch (IOException e) {e.printStackTrace();}
        try {pvz_logo=ImageIO.read(getClass().getResourceAsStream("/Resource/MenuGame/pvz_logo.png"));
        } catch (IOException e) {e.printStackTrace();}
        try {playButton=ImageIO.read(getClass().getResourceAsStream("/Resource/MenuGame/PlayDemo.png"));
        } catch (IOException e) {e.printStackTrace();}
    }
    public void render(Graphics2D g2){
        g2.drawImage(menuGame,0,0,1300,750,null);
        g2.drawImage(pvz_logo, 300,50,700,200, null);
        g2.drawImage(playButton,bounds.x,bounds.y,bounds.width,bounds.height, null);
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public void handleMouseClick(int mouseX, int mouseY) {
        if(this.getBounds().contains(mouseX,mouseY)){
            gameLoop.getPlaying().resetGame();
            GameScenes.setGameScenes(GameScenes.PLAYING); 
            gameLoop.repaint();
        }
    }

}
