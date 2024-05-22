<<<<<<< HEAD

import java.awt.Component;
=======
>>>>>>> c4fd7cb19fbb9c888c3a135a60ca636002898b7b
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class GameFrame extends JFrame {
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;
    Exam1_HCMIU gamePanel;
    GameLoop gameLoop;

    public GameFrame() {
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT) / 2, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gamePanel = new Exam1_HCMIU();
        gameLoop = new GameLoop();
        Component Exam1_HCMIU;
        add(Exam1_HCMIU);


    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public void startGame() {
        Thread gameThread = new Thread(gameLoop);
        gameThread.start();
    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
        gameFrame.startGame();
    }
}