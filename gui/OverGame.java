import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OverGame {
    private Image OverScence;
    private Image NewGame;
    private Image Exit;
    private Rectangle bounds1;
    private Rectangle bounds2;
    private boolean checkPlay;
    private boolean checkExit;
    public OverGame() {
        importImage();
        bounds1=new Rectangle(500,700,200,200);
        bounds2=new Rectangle(800,700,200,200);}
    public Rectangle getBounds1() {return bounds1;}
    public void setBounds1(Rectangle bounds1) {this.bounds1 = bounds1;}
    public Rectangle getBounds2() {return bounds2;}
    public void setBounds2(Rectangle bounds2) {this.bounds2 = bounds2;}
    public boolean isCheckPlay() {return checkPlay;}
    public void setCheckPlay(boolean checkPlay) {this.checkPlay = checkPlay;}
    public boolean isCheckExit() {return checkExit;}
    public void setCheckExit(boolean checkExit) {this.checkExit = checkExit;}
    public void importImage(){
        try {OverScence=ImageIO.read(new File("D:/Code/GitHub/OOP-Game-OcCho/res/WinScence/GameOver.png"));
        } catch (IOException e) {e.printStackTrace();}
        try {NewGame=ImageIO.read(new File("D:/Code/GitHub/OOP-Game-OcCho/res/WinScence/GOver_NewGame_Button.png"));
        } catch (IOException e) {e.printStackTrace();}
        try {Exit=ImageIO.read(new File("D:/Code/GitHub/OOP-Game-OcCho/res/WinScence/GOver_ExitGame_Button.png"));
        } catch (IOException e) {e.printStackTrace();}
    }
    
    public void render(Graphics2D g2){
        g2.drawImage(OverScence, (1300-800)/2,(750-550)/2,800,550, null);
        g2.drawImage(NewGame, bounds1.x,bounds1.y,(int) bounds1.getWidth(),(int )bounds1.getHeight(),null);
        g2.drawImage(Exit, bounds2.x,bounds2.y,(int) bounds2.getWidth(),(int )bounds2.getHeight(),null);
    }
    public void checkPlay(int mouseX,int mouseY){
        if(this.getBounds1().contains(mouseX,mouseY)){
            setCheckPlay(true);
        }
    }
    public void checkExit(int mouseX,int mouseY){
        if(this.getBounds2().contains(mouseX,mouseY)){
            setCheckExit(true);
        }
    }
}