import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import PvZ.Lawn.LawnManager;
import PvZ.Plants.OptionPlants;
import PvZ.Plants.PlantsManager;
import PvZ.Zombies.Zombie;
import PvZ.Zombies.ZombieManager;
import Sun.Sun;
import Sun.SunDrop;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel {
    private SunDrop sunDrop;
    private PlantsManager plantsManager;
    private ZombieManager zombieManager;
    private OptionPlants optionPlants;
    private LawnManager lawnManager;
    Background bg = new Background(this);
    Random random=new Random();
    public GamePanel() {
        setDoubleBuffered(true);
        sunDrop = new SunDrop();
        optionPlants = new OptionPlants();
        lawnManager = new LawnManager();
        plantsManager = new PlantsManager();
        zombieManager = new ZombieManager();
        startSunSpawner();
        LawnAction();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    public void handleMouseClick(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        sunDrop.handleSunClick(mouseX, mouseY);
        if (!optionPlants.isChoose()) {
            optionPlants.handPlantsChoose(mouseX, mouseY);
        } else {
            int previous_X = optionPlants.getXSpwan();
            int previous_Row = optionPlants.getRowSpawn();
            optionPlants.ChoosePositionForPlant(mouseX, mouseY);

            if (previous_X != optionPlants.getXSpwan() || previous_Row != optionPlants.getRowSpawn()) {
                plantsManager.SpawnPlants(optionPlants.getType(), optionPlants.getXSpwan(), optionPlants.getRowSpawn(), sunDrop);
                optionPlants.setChoose(false);
                repaint();
            }
        }
    }

    public void startSunSpawner() {
        Timer timer = new Timer();
        Random random = new Random();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sunDrop.spawnSun();
            }
        }, 0, 2000 + random.nextInt(2500));

        Timer movementTimer = new Timer();
        movementTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sunDrop.sunMove();
                repaint();
            }
        }, 0, 125);
    }

    public void LawnAction() {
        lawnManager.SpawnLawn();
        Timer movementTimer = new Timer();
        movementTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (zombieManager.ZombieList()) {
                    lawnManager.Action(zombieManager.ZombieList());
                }
                repaint();
            }
        }, 0, 125);
    }

    public void startCharacterSpawnAndAction() {
        Timer timers = new Timer();
        timers.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(ZombieManager.getLevel()==1){
                    if (ZombieManager.getCountZombie() <= 10) {
                        zombieManager.SpawnZombie();
                    }
                }
                if(ZombieManager.getLevel()==2){
                    if (ZombieManager.getCountZombie() <= 20) {
                        zombieManager.SpawnZombie();
                    }
                }
                if(ZombieManager.getLevel()==3){
                    if (ZombieManager.getCountZombie() <= 30) {
                        zombieManager.SpawnZombie();
                    }
                }
                if(ZombieManager.getLevel()==4){
                    if (ZombieManager.getCountZombie() <= 40) {
                        zombieManager.SpawnZombie();
                    }
                }
                if(ZombieManager.getLevel()==5){
                    if (ZombieManager.getCountZombie() <= 50) {
                        zombieManager.SpawnZombie();
                    }
                }
                if(ZombieManager.getLevel()==6){
                    if (ZombieManager.getCountZombie() <= 60) {
                        zombieManager.SpawnZombie();
                    }
                }                    
                repaint();
        }
        }, 0, new Random().nextInt(5000) + 2000);

        Timer movetimers = new Timer();
        movetimers.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (zombieManager.ZombieList()) {
                    ArrayList<Zombie> zombiesCopy = new ArrayList<>(zombieManager.ZombieList());
                    plantsManager.PlantsAction(zombiesCopy, sunDrop);
                    zombieManager.ZombieAction(plantsManager.getPlantsList());
                    zombieManager.ZombieDead();
                    plantsManager.DeadPlant();
                }
                repaint();
            }
        }, 0, 200);
    }

    public void startGame() {
        startCharacterSpawnAndAction();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        bg.render(g2);
        for (Sun sun : sunDrop.SunList()) {
            sun.render(g2);
        }
        plantsManager.render(g2, zombieManager.ZombieList(), sunDrop);
        lawnManager.render(g2);
        sunDrop.drawStorage(g2);
        synchronized (zombieManager.ZombieList()) {
            ArrayList<Zombie> zombiesCopy = new ArrayList<>(zombieManager.ZombieList());
            for (Zombie zombie : zombiesCopy) {
                zombie.renderZombiesAction(g2);
            }
        }
    }
}