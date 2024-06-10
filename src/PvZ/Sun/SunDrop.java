package Sun;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.awt.*;
import game.audio.AudioGame;
public class SunDrop extends JPanel {
    private ArrayList<Sun> SunList = new ArrayList<>();
    private ArrayList<Sun> sunsToRemoveByClicked = new ArrayList<>();
    ArrayList<Sun> sunsToRemoveByTime = new ArrayList<>();
    private int TimeCDrop = 2000;
    private int startX;
    private int startY;
    private int EndY = 750 / 2;
    private Image storageImage;
    Random random = new Random();
    private int sunscore=1000;
    public AudioGame sound = new AudioGame();
    public SunDrop() {
        try {
            this.storageImage = ImageIO.read(getClass().getResourceAsStream("/Sun/SunCollected.png"));
        } catch (IOException e) {
            e.printStackTrace();}}

    public ArrayList<Sun> SunList() {return SunList;}

    public int getTimeCDrop() {return TimeCDrop;}

    public int getStartX() {return startX;}

    public int getStartY() {return startY;}

    public int getEndY() {return EndY;}

    public int getSunscore() {return sunscore;}

    public void setSunscore(int sunscore) {this.sunscore = sunscore;}

    public synchronized void spawnSun() {
        startX = 400 + random.nextInt(800);
        startY = 50 + random.nextInt(250);
        SunList.add(new Sun(startX, startY));}

    public synchronized void handleSunClick(int mouseX, int mouseY) {
        sunsToRemoveByClicked.clear();
        for (Sun sun : SunList) {
            if (sun.getBounds().contains(mouseX, mouseY)) {
                sunsToRemoveByClicked.add(sun);
                sound.sunCollected();
                sun.acceptCollected(true);
                sunscore+=25;}}
        SunList.removeAll(sunsToRemoveByClicked);
        repaint();

    }

    public synchronized void sunMove() {
        for (Sun sun : SunList) {
            if (sun.getY() < EndY) {
                sun.setY(sun.getY() + 4);
            } 
            else {
                sun.decrementDestruct();
                if (sun.getDestruct() == 0) {
                    sunsToRemoveByTime.add(sun);
                }
            }
        }
        SunList.removeAll(sunsToRemoveByTime);
    }
    public synchronized void render(Graphics2D g2){
        for(Sun sun:SunList){
            sun.render(g2);
        }
    }
    public synchronized void drawStorage(Graphics g){
        Graphics2D g2n = (Graphics2D) g;
        g.drawImage(storageImage, 10,20 ,100,65,null);
        Font font1=new Font("Time New Roman",Font.BOLD,18);
        g2n.setFont(font1);
        g2n.setColor(Color.WHITE);
        g2n.drawString(String.format("%d",sunscore),67,58);
        Font font2=new Font("Time New Roman",Font.BOLD,15);
        g2n.setFont(font2);
        g2n.setColor(Color.YELLOW);
        g2n.drawString(String.format("%d",50), 190, 75);
        g2n.drawString(String.format("%d",100 ),295,75);
        g2n.drawString(String.format("%d",50 ),410,75);
    }

}