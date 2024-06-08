import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import PvZ.Zombies.ZombieManager;
public class OverGame {
    private Image overScene;
    private Image newGame;
    private Image exit;
    private Rectangle bounds1;
    private Rectangle bounds2;
    private boolean checkPlay;
    private boolean checkExit;
    private GameLoop gameLoop;
    public OverGame(GameLoop gameLoop) {
        this.gameLoop=gameLoop;
        importImage();
        bounds1 = new Rectangle(400, 500, 200, 100);
        bounds2 = new Rectangle(800, 500, 200, 100);
    }

    public Rectangle getBounds1() {
        return bounds1;
    }

    public void setBounds1(Rectangle bounds1) {
        this.bounds1 = bounds1;
    }

    public Rectangle getBounds2() {
        return bounds2;
    }

    public void setBounds2(Rectangle bounds2) {
        this.bounds2 = bounds2;
    }

    public boolean isCheckPlay() {
        return checkPlay;
    }

    public void setCheckPlay(boolean checkPlay) {
        this.checkPlay = checkPlay;
    }

    public boolean isCheckExit() {
        return checkExit;
    }

    public void setCheckExit(boolean checkExit) {
        this.checkExit = checkExit;
    }

    public void importImage() {
        try {
            overScene = ImageIO.read(getClass().getResourceAsStream("/WinScence/GameOver.png"));
            newGame = ImageIO.read(getClass().getResourceAsStream("/WinScence/GOver_NewGame_Button.png"));
            exit = ImageIO.read(getClass().getResourceAsStream("/WinScence/GOver_ExitGame_Button.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics2D g2) {
        g2.drawImage(overScene, 0, 0, 1300, 750, null);
        g2.drawImage(newGame, bounds1.x, bounds1.y, bounds1.width, bounds1.height, null);
        g2.drawImage(exit, bounds2.x, bounds2.y, bounds2.width, bounds2.height, null);
        Font font1=new Font("Time New Roman",Font.BOLD,70);
        g2.setFont(font1);
        g2.setColor(Color.YELLOW);
        g2.drawString(String.format("%d",ZombieManager.getPoints()),650,280);
    }

    public void handleMouseClick(int mouseX, int mouseY) {
        if (this.getBounds1().contains(mouseX, mouseY)) {
            gameLoop.getPlaying().resetGame();
            GameScenes.setGameScenes(GameScenes.PLAYING); 
            gameLoop.repaint(); 
        }
        if (this.getBounds2().contains(mouseX, mouseY)) {
            gameLoop.getMenuGame();
            GameScenes.setGameScenes(GameScenes.MENU);
            gameLoop.repaint();
        }
    }
    public void update(){}
}