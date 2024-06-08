import java.awt.*;
import java.util.*;
import javax.swing.JPanel;
import PvZ.Lawn.LawnManager;
import PvZ.Plants.OptionPlants;
import PvZ.Plants.PlantsManager;
import PvZ.Plants.Shovel;
import PvZ.Zombies.FlagMeter;
import PvZ.Zombies.Zombie;
import PvZ.Zombies.ZombieManager;
import Sun.SunDrop;

public class Playing extends JPanel {
    private SunDrop sunDrop;
    private Shovel shovel;
    private PlantsManager plantsManager;
    private ZombieManager zombieManager;
    private OptionPlants optionPlants;
    private LawnManager lawnManager;
    private GameLoop gameLoop;
    private Background bg;
    private FlagMeter flag;
    private Random random;
    private Timer sunTimer;
    private Timer sunMovementTimer;
    private Timer lawnActionTimer;
    private Timer zombieSpawnTimer;
    private Timer zombieActionTimer;
    private boolean gameEnded = false;
    public Playing(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
        initializeGameComponents();
    }
    // Restart New Game
    public void initializeGameComponents() {
        sunDrop = new SunDrop();
        optionPlants = new OptionPlants();
        lawnManager = new LawnManager();
        plantsManager = new PlantsManager();
        zombieManager = new ZombieManager();
        ZombieManager.resetStaticVariables();
        bg = new Background(this);
        flag=new FlagMeter(zombieManager);
        shovel=new Shovel(plantsManager,optionPlants);
        random = new Random();
        gameEnded = false;
    }

    public void stopGame() {
        
        if (sunTimer != null) {
            sunTimer.cancel();
        }
        if (sunMovementTimer != null) {
            sunMovementTimer.cancel();
        }
        if (lawnActionTimer != null) {
            lawnActionTimer.cancel();
        }
        if (zombieSpawnTimer != null) {
            zombieSpawnTimer.cancel();
        }
        if (zombieActionTimer != null) {
            zombieActionTimer.cancel();
        }
        gameEnded = false;
    }
    
    public void startGame() {
        startSunSpawner();
        LawnAction();
        startCharacterSpawnAndAction();
    }

    public void resetGame() {
        stopGame();
        initializeGameComponents();
        startGame();
    }

    //User Mouse Click
    public void handleMouseClick(int mouseX, int mouseY) {
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
        if(shovel.isChoose()==false){
            shovel.chooseShovel(mouseX, mouseY);
        } else{shovel.chooseShovel(mouseX, mouseY);
                if(shovel.isChoose()==true){
                shovel.RemovePlant(mouseX, mouseY);
            }
        }
    }

    // Game Action
    public void startSunSpawner() {
        sunTimer = new Timer();
        sunTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (sunDrop) {
                    sunDrop.spawnSun();
                }
                repaint();
            }
        }, 0, 2000 + random.nextInt(2500));

        sunMovementTimer = new Timer();
        sunMovementTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (sunDrop) {
                    sunDrop.sunMove();
                }
                repaint();
            }
        }, 0, 125);
    }

    public void LawnAction() {
        lawnManager.SpawnLawn();
        lawnActionTimer = new Timer();
        lawnActionTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (zombieManager.ZombieList()) {
                    lawnManager.Action(zombieManager.ZombieList());
                    lawnManager.LawnDead();
                }
                repaint();
            }
        }, 0, 125);
    }

    public void startCharacterSpawnAndAction() {
        System.out.println(ZombieManager.getLevel());
        int maxZombies = ZombieManager.getLevel() * 10;
        zombieSpawnTimer = new Timer();
        zombieSpawnTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (zombieManager.ZombieList()) {
                    if (ZombieManager.getCountZombie() < maxZombies) {
                        zombieManager.SpawnZombie();
                        repaint();
                    }
                    if (zombieManager.ZombieList().isEmpty() && ZombieManager.getCountZombie() >= maxZombies && gameEnded==false) {
                        gameEnded = true;
                        GameScenes.setGameScenes(GameScenes.OVERGAME);
                        gameLoop.repaint();
                    }
                }
            }
        }, 0, new Random().nextInt(5000) + 2000);

        zombieActionTimer = new Timer();
        zombieActionTimer.scheduleAtFixedRate(new TimerTask() {
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

    public void render(Graphics2D g2) {
        bg.render(g2);
        flag.render(g2);
        shovel.render(g2);
        sunDrop.render(g2);
        plantsManager.render(g2, zombieManager.ZombieList(), sunDrop);
        lawnManager.render(g2);
        sunDrop.drawStorage(g2);
        zombieManager.render(g2);
    }
}