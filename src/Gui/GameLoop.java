package Gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GameLoop extends JPanel implements Runnable {
    private Boolean isRunning;
    private final long FPS = 60;
    private final long PERIOD = 1000 * 1000000 / FPS; 
    private MenuState menuGame;
    private Playing playing;
    private GameOverState overGame;

     public GameLoop() {
        isRunning = true;
        initclasses();
        MouseManager mouseListener = new MouseManager(this, menuGame,playing, overGame);
        this.addMouseListener(mouseListener);
    }

    public void stop() {
        isRunning = false;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public MenuState getMenuGame() {
        return menuGame;    
    }

    public Playing getPlaying() {
        return playing;
    }

    public GameOverState getOverGame() {
        return overGame;
    }

    public void initclasses() {
        menuGame = new MenuState(this);
        playing = new Playing(this);
        overGame = new GameOverState(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        render(g2);
    }
    public void render(Graphics2D g2) {
        switch (GameScenes.gameScenes) {
            case MENU:
                menuGame.render(g2);
                break;
            case PLAYING:
                playing.render(g2);
                break;
            case OVERGAME:
                overGame.render(g2);
                break;
        }
    }
    @Override
    public void run() {
        long beginTime;
        long sleepTime;
        beginTime = System.nanoTime();
        while (isRunning) {
            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = PERIOD - deltaTime;
            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime / 1000000);
                } else {
                    Thread.sleep(PERIOD / 2000000);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            repaint();
            beginTime = System.nanoTime();
        }
    }
}
