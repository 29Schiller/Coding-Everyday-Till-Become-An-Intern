
import java.awt.*;
import javax.swing.*;

public class Exam1_HCMIU {
    
    private Image[] buttonOfMenu;
    private Toolkit t = Toolkit.getDefaultToolkit();
    
    private void importImg(){
        //buttonOfMenu = new Image[2];
        try {
            buttonOfMenu[0] = t.getImage(getClass().getResource("/Scene/PLAY.png"));
            buttonOfMenu[1] = t.getImage(getClass().getResource("/Scene/EXIT.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawImg(Graphics g){
        g.drawImage(buttonOfMenu[0], 850, 400, 130, 45, null);
        g.drawImage(buttonOfMenu[1], 850, 470, 130, 45, null);
    }

    public void render(Graphics g, Image img) {
        g.drawImage(img,0,0, , , null);
//        drawButtons(g);
        importImg();
        drawImg(g);
    }

	public static void main(String[] args) {
		
	}
}
