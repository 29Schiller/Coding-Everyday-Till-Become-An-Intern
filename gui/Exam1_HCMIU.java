
import java.awt.*;
import javax.swing.*;

public class Exam1_HCMIU {
    
    private Image[] buttonOfMenu;
    private Toolkit t = Toolkit.getDefaultToolkit();
    
    private void importImg(){
        //buttonOfMenu = new Image[2];
        try {
            buttonOfMenu[0] = t.getImage(getClass().getResource("res/pvz_logo.png"));
            buttonOfMenu[1] = t.getImage(getClass().getResource("res/Button/PlayDemo.png"));
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
        Image background = t.getImage(getClass().getResource("res/wallpaper.jpg"));
        g.drawImage(background,0,0, 1600, 750, null);
//        drawButtons(g);
        importImg();
        drawImg(g);
    }

	public static void main(String[] args) {
        JFrame windowFrame = new JFrame();
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setSize(1600,750);
        windowFrame.setVisible(true);
        windowFrame.setLayout(null);
        
		JPanel picturPanel = new JPanel();
        picturPanel.render();
	}
}
