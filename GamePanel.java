import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
public class GamePanel extends JPanel implements Runnable{
    private Thread thread;
    private Boolean isRunning;
    private Image background;

    public void paint(Graphics g2){
        background = new ImageIcon("D:/Code/OOP/Lab Class/lab/Project/Map_1.jpg").getImage();
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
    public void startgame(){
        if(thread==null){
            isRunning=true;
            thread=new Thread(this);
            thread.start();
        }
    }
    @Override
    public void run(){
        long FPS=60;
        long Period=1000*1000000/FPS;//đơn vị nano second
        long beginTime;
        long sleepTime;
        int a=0;
        beginTime=System.nanoTime();
        while(isRunning==true){
            //update game
            //render game
            System.out.println("a="+(a++));
            long deltaTime=System.nanoTime()-beginTime;
            sleepTime=Period-deltaTime;
            try{
                if(sleepTime>0){
                    Thread.sleep(sleepTime/1000000);}
                else {
                    Thread.sleep(Period/2000000);
                }
            }catch(InterruptedException ex){}
            beginTime=System.nanoTime();
        }
    }
}