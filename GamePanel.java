package Screnes;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
public class GamePanel extends JPanel implements Runnable{
    private Thread thread;
    private Boolean isRunning;
    private Toolkit t = Toolkit.getDefaultToolkit();
    Background bg=new Background(this);

    public GamePanel(){
    }

    //Draw
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        bg.render(g2);
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