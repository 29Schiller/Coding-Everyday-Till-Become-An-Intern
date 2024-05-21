import java.awt.*
;

import javax.swing.ImageIcon;
public class Exam1_HCMIU {
	public final Component panel = null;

	//Import Background Scene
	public void showMenuState(){
	ImageIcon Menubackground = new ImageIcon("res/wallpaper.jpg");
	Menubackground.draw();
	}
	
	public void showTitle(){
		ImageIcon Title = new ImageIcon("res/pvz_logo.png");
		float rate = (float) 0.7;
		float width = Title.getIconWidth() * GameFrame.SCREEN_WIDTH * rate;
		float height = Title.getIconHeight() * GameFrame.SCREEN_HEIGHT * rate;
		float posX = 1300/2 - width/2;
		float posY = 750*(float)(0.2) - height/2;

		Title.draw();
		}

	//Import Button Start
	public void showButton(){
		ImageIcon Start = new ImageIcon("res/PlayDemo.png");
		float rate = 0.9f;
		float width = Start.getIconWidth() * GameFrame.SCREEN_WIDTH * rate;
		float height = Start.getIconHeight() * GameFrame.SCREEN_HEIGHT * rate;
		
		float posX = 1300  /2 - width/2;
		float posY = 750 * (float)(0.7) - height/2;

		
		Start.draw(posX, posY, width, height);
	}

	//Import Theme Sound

}