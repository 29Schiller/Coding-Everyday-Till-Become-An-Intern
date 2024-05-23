
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class World extends JPanel implements Runnable {
    private final int screenWidth = 1070, screenHeight = 576;
    private ArrayList<Image> img = new ArrayList<>();
    private Lose lose;
    private MenuGame menuGame;
    private Playing playing;
    private Setting setting;
    private Win win;

    private double FPS_SET = 60.0;
    private double UPS_SET = 60.0;
    private MyMouseListener myMouseListener;
    private KeyBoardListener keyBoardListener;

    private Toolkit t = Toolkit.getDefaultToolkit();
    private int frameCount = 0;
    private boolean FPSstop = false;
    private boolean UPSstop = false;

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }

    public World() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        initInput();
        initClasses();
        importImg();
        start();

    }

    public int getScreenHeight() {
        return screenHeight;
    }
    public int getScreenWidth(){
        return screenWidth;
    }

    public Lose getLose() {
       return lose;
    }

    public MenuGame getMenuGame() {
        return menuGame;
    }

    public Playing getPlaying() {
        return playing;
   }

    public Win getWin() {
        return win;
    }

    public Setting getSetting() {
        return setting;
    }

    public void initInput() {
        myMouseListener = new MyMouseListener(this);
        keyBoardListener = new KeyBoardListener(this);
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyBoardListener);
        setFocusable(true);
        requestFocus();
    }

    public void initClasses() {
        menuGame = new MenuGame(this);
        playing = new Playing(this);
        win = new Win(this);
        lose = new Lose(this);
        setting = new Setting(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        render(g);
    }

    public void render(Graphics g) {
        switch (GameScenes.gameScenes) {
            case MENU:
                menuGame.render(g, img.get(0));
                break;
            case PLAYING:
                playing.render(g, img.get(1));
                break;
            case LOSE:
                lose.render(g, img.get(2));
                break;
            case SETTING:
                setting.render(g, img.get(3));
                break;
            case WIN:
                win.render(g,img.get(4));
        }
    }

    public void updates() {
        switch (GameScenes.gameScenes) {
            case MENU:
                getMenuGame().updates();
                break;
            case PLAYING:
                getPlaying().updates();
                break;
            case LOSE:
                getLose().updates();
               break;
            case SETTING:
                getSetting().updates();
                break;
            case WIN:
                getWin().updates();
        }
    }

    public void importImg() {
        img.add(t.getImage(getClass().getResource("/Scene/menu.png")));
        img.add(t.getImage(getClass().getResource("/Scene/background_2.jpg")));
        img.add(t.getImage(getClass().getResource("/Scene/lose.png")));
        img.add(t.getImage(getClass().getResource("/Scene/game paused2.png")));
        img.add(t.getImage(getClass().getResource("/Scene/win.png")));
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastUpdate = System.nanoTime();
        int updates = 0;
        int frames = 0;
        long lastTimeCheck = System.currentTimeMillis();
        long now;
        while (true) {
            now = System.nanoTime();
            //repaint game
            if (now - lastUpdate >= timePerFrame) {
                lastFrame = now;
                frames++;
                repaint();
            }

            if (now - lastUpdate >= timePerUpdate) {
                lastUpdate = now;
                updates++;
                updates();
            }

            //check FPS & UPS
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }






}
