package Gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseManager extends MouseAdapter{
    private MenuState menuGame;
    private GameOverState overGame;
    private Playing playing;
    private GameLoop gameLoop;

    public MouseManager(GameLoop gameLoop, MenuState menuGame,Playing playing, GameOverState overGame) {
        this.gameLoop = gameLoop;
        this.menuGame = menuGame;
        this.overGame = overGame;
        this.playing=playing;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (GameScenes.gameScenes == GameScenes.MENU) {
            menuGame.handleMouseClick(mouseX, mouseY);
        }else if(GameScenes.gameScenes==GameScenes.PLAYING){
            playing.handleMouseClick(mouseX, mouseY);
        }
         else if (GameScenes.gameScenes == GameScenes.OVERGAME) {
           overGame.handleMouseClick(mouseX, mouseY);
        }
    }
}
